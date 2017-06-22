USE parking;

DROP FUNCTION IF EXISTS getLotKey;
CREATE FUNCTION getLotKey(nam TEXT)
	RETURNS INT DETERMINISTIC
	RETURN (SELECT lotKey FROM parking.Lot WHERE name = nam LIMIT 1);

DROP FUNCTION IF EXISTS getViolationKey;
CREATE FUNCTION getViolationKey(nam TEXT)
	RETURNS INT DETERMINISTIC
	RETURN (SELECT violationKey FROM parking.Violation WHERE name = nam LIMIT 1);

DROP FUNCTION IF EXISTS getOwnerKey;
CREATE FUNCTION getOwnerKey(iNum TEXT)
	RETURNS INT DETERMINISTIC
	RETURN (SELECT ownerKey FROM parking.Owner WHERE iNumber = iNum);

DROP FUNCTION IF EXISTS getStateKey;
CREATE FUNCTION getStateKey(abbr TEXT)
	RETURNS INT DETERMINISTIC
	RETURN (SELECT stateKey FROM parking.vwStates WHERE abbrev = abbr LIMIT 1);

DROP FUNCTION IF EXISTS getColorKey;
CREATE FUNCTION getColorKey(nam TEXT)
	RETURNS INT DETERMINISTIC
	RETURN (SELECT colorKey FROM parking.vwColors WHERE name = nam LIMIT 1);

DROP FUNCTION IF EXISTS getBodyKey;
CREATE FUNCTION getBodyKey(name TEXT)
	RETURNS INT DETERMINISTIC
	RETURN (SELECT bodyKey FROM parking.vwBodies WHERE term = name);

DROP FUNCTION IF EXISTS getPlateKey;
CREATE FUNCTION getPlateKey(state TEXT, num TEXT)
	RETURNS INT DETERMINISTIC
	RETURN (SELECT plateKey FROM parking.Plate
		WHERE stateKey = getStateKey(state) AND number = num);

DROP FUNCTION IF EXISTS getVehicleKey;
CREATE FUNCTION getVehicleKey(vi TEXT)
	RETURNS INT DETERMINISTIC
	RETURN (SELECT vehicleKey FROM parking.Vehicle WHERE VIN = vi LIMIT 1);

DROP FUNCTION IF EXISTS getPermitTypeKey;
CREATE FUNCTION getPermitTypeKey(typ TEXT)
	RETURNS INT DETERMINISTIC
	RETURN (SELECT permitTypeKey FROM parking.vwPermitTypes WHERE term = typ);

DROP FUNCTION IF EXISTS getPermitKey;
CREATE FUNCTION getPermitKey(iNum TEXT, typ TEXT)
	RETURNS INT DETERMINISTIC
	RETURN (SELECT permitKey FROM parking.Permit
		WHERE ownerKey = getOwnerKey(iNum) AND permitTypeKey = getPermitTypeKey(typ)
		ORDER BY expires
		LIMIT 1);

DROP FUNCTION IF EXISTS getSightingKey;
CREATE FUNCTION getSightingKey(approx DATE, lotK INT, plateK INT)
	RETURNS INT DETERMINISTIC
	RETURN (SELECT sightingKey FROM parking.Sighting
		WHERE lotKey = lotK AND plateKey = plateK
		ORDER BY ABS(dateSighted - approx)
		LIMIT 1);


DROP FUNCTION IF EXISTS getPermit;
DELIMITER //
CREATE FUNCTION getPermit(plateKey INT)
RETURNS INT DETERMINISTIC
BEGIN
	RETURN 0;
END//
DELIMITER ;

DROP FUNCTION IF EXISTS isViolation;
DELIMITER //
CREATE FUNCTION isViolation(permitKey INT, lotKey INT)
RETURNS BOOLEAN DETERMINISTIC
BEGIN
	DECLARE type INT;
	DECLARE viol BOOLEAN;
	SET type = (SELECT term
		FROM parking.Permit AS p
		INNER JOIN parking.PermitType AS t ON t.permitTypeKey = p.permitTypeKey
		INNER JOIN parking.Lookup AS k ON k.lookupKey = t.permitTypeKey
		WHERE p.permitKey = permitKey);
	CASE type
		WHEN 'A' THEN SET viol = TRUE;
		WHEN 'F' THEN SET viol = FALSE;
		ELSE SET viol = FALSE;
	END CASE;
	RETURN viol;
END//
DELIMITER ;

DROP FUNCTION IF EXISTS getCitationCost;
DELIMITER //
CREATE FUNCTION getCitationCost(_sightingKey INT, _violationKey INT)
RETURNS BOOLEAN DETERMINISTIC
BEGIN
	DECLARE _whenSighted, _plateDate, _vehicleDate, _yearAgo DATETIME;
	DECLARE _plateKey, _vehicleKey, _ownerKey, _numCits INT;

	-- Get the ownerKey for the person responsible for this citation.
	SET _whenSighted =
		(SELECT s.dateSighted FROM parking.Sighting AS s
		 WHERE s.sightingKey = _sightingKey);
	SET _plateKey =
		(SELECT s.plateKey FROM parking.Sighting AS s
		 WHERE s.sightingKey = _sightingKey);
	SET _plateDate =
		(SELECT MIN(vp.dateEntered) FROM parking.VehicleHasPlate AS vp
		 WHERE vp.plateKey = _plateKey AND vp.dateEntered <= _whenSighted);
	SET _vehicleKey =
		(SELECT vp.vehicleKey FROM parking.VehicleHasPlate AS vp
		 WHERE vp.plateKey = _plateKey AND vp.dateEntered = _plateDate);
	SET _vehicleDate =
		(SELECT MIN(ov.dateEntered) FROM parking.OwnerHasVehicle AS ov
		 WHERE ov.vehicleKey = _vehicleKey AND dateEntered <= _whenSighted);
	SET _ownerKey =
		(SELECT ov.ownerKey FROM parking.OwnerHasVehicle AS ov
		WHERE ov.vehicleKey = _vehicleKey AND ov.dateEntered = _vehicleDate);

	-- Count how many citations this person has received in the last year.
	SET _yearAgo = DATE_SUB(CURDATE(), INTERVAL 1 YEAR);
	SET _numCits =
		(SELECT COUNT(citationKey) FROM parking.vwCitationJoinOwner AS cjo
		 WHERE cjo.ownerKey = _ownerKey AND dateSighted > _yearAgo);

	RETURN
		(SELECT first + increase * _numCits
		 FROM parking.Violation AS v
		 WHERE v.violationKey = _violationKey);
END//
DELIMITER ;
