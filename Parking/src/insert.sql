START TRANSACTION;
USE parking;

DELETE FROM Citation;
DELETE FROM Sighting;
DELETE FROM Permit;
DELETE FROM VehicleHasPlate;
DELETE FROM Plate;
DELETE FROM OwnerHasVehicle;
DELETE FROM Owner;
DELETE FROM Vehicle;
DELETE FROM Violation;
DELETE FROM Lot;
DELETE FROM PermitType;
DELETE FROM Lookup;

INSERT INTO Lookup (band, term, descr)
VALUES
	('color', 'red', NULL),
	('color', 'green', NULL),
	('color', 'blue', NULL),
	('color', 'yellow', NULL),
	('color', 'purple', NULL),
	('color', 'orange', NULL),
	('color', 'black', NULL),
	('color', 'white', NULL),
	('color', 'gold', NULL),
	('color', 'silver', NULL),
	('color', 'charcoal', NULL),
	('color', 'brown', NULL),

	('body', 'pickup', NULL),
	('body', 'sedan', NULL),
	('body', 'coupe', NULL),
	('body', 'SUV', NULL),
	('body', 'bicycle', NULL),
	('body', 'motorcycle', NULL),

	('make', 'Ford', NULL),
	('make', 'Lincoln', NULL),
	('make', 'Mercury', NULL),
	('make', 'Mazda', NULL),
	('make', 'Buick', NULL),
	('make', 'Chevrolet', NULL),
	('make', 'Oldsmobile', NULL),
	('make', 'Pontiac', NULL),
	('make', 'Opel', NULL),
	('make', 'GMC', NULL),
	('make', 'Cadillac', NULL),
	('make', 'Saturn', NULL),
	('make', 'Jeep', NULL),
	('make', 'Tesla', NULL),
	('make', 'BMW', NULL),
	('make', 'Porsche', NULL),
	('make', 'Volkswagen', NULL),
	('make', 'Citroen', NULL),
	('make', 'Fiat', NULL),
	('make', 'Datsun', NULL),
	('make', 'Misubishi', NULL),
	('make', 'Honda', NULL),
	('make', 'Toyota', NULL),
	('make', 'Daewoo', NULL),
	('make', 'Hyundai', NULL),
	('make', 'Kawasaki', NULL),

	('state', 'AL', 'Alabama'),
	('state', 'AK', 'Alaska'),
	('state', 'AS', 'American Samoa'),
	('state', 'AZ', 'Arizona'),
	('state', 'AR', 'Arkansas'),
	('state', 'CA', 'California'),
	('state', 'CO', 'Colorado'),
	('state', 'CT', 'Connecticut'),
	('state', 'DE', 'Delaware'),
	('state', 'DC', 'Dist. of Columbia'),
	('state', 'FL', 'Florida'),
	('state', 'GA', 'Georgia'),
	('state', 'GU', 'Guam'),
	('state', 'HI', 'Hawaii'),
	('state', 'ID', 'Idaho'),
	('state', 'IL', 'Illinois'),
	('state', 'IN', 'Indiana'),
	('state', 'IA', 'Iowa'),
	('state', 'KS', 'Kansas'),
	('state', 'KY', 'Kentucky'),
	('state', 'LA', 'Louisiana'),
	('state', 'ME', 'Maine'),
	('state', 'MD', 'Maryland'),
	('state', 'MH', 'Marshall Islands'),
	('state', 'MA', 'Massachusetts'),
	('state', 'MI', 'Michigan'),
	('state', 'FM', 'Micronesia'),
	('state', 'MN', 'Minnesota'),
	('state', 'MS', 'Mississippi'),
	('state', 'MO', 'Missouri'),
	('state', 'MT', 'Montana'),
	('state', 'NE', 'Nebraska'),
	('state', 'NV', 'Nevada'),
	('state', 'NH', 'New Hampshire'),
	('state', 'NJ', 'New Jersey'),
	('state', 'NM', 'New Mexico'),
	('state', 'NY', 'New York'),
	('state', 'NC', 'North Carolina'),
	('state', 'ND', 'North Dakota'),
	('state', 'MP', 'Northern Marianas'),
	('state', 'OH', 'Ohio'),
	('state', 'OK', 'Oklahoma'),
	('state', 'OR', 'Oregon'),
	('state', 'PW', 'Palau'),
	('state', 'PA', 'Pennsylvania'),
	('state', 'PR', 'Puerto Rico'),
	('state', 'RI', 'Rhode Island'),
	('state', 'SC', 'South Carolina'),
	('state', 'SD', 'South Dakota'),
	('state', 'TN', 'Tennessee'),
	('state', 'TX', 'Texas'),
	('state', 'UT', 'Utah'),
	('state', 'VT', 'Vermont'),
	('state', 'VA', 'Virginia'),
	('state', 'VI', 'Virgin Islands'),
	('state', 'WA', 'Washington'),
	('state', 'WV', 'West Virginia'),
	('state', 'WI', 'Wisconsin'),
	('state', 'WY', 'Wyoming'),

	('permit', 'X',   'dignitary'),
	('permit', 'ES',  'employee service'),
	('permit', 'A',   'faculty and staff'),
	('permit', 'FN',  'north lots'),
	('permit', 'FS',  'south lots'),
	('permit', 'N',   'north lots'),
	('permit', 'S',   'south lots'),
	('permit', 'C',   'church lot (fourth ward meeting house)'),
	('permit', 'H',   'housing'),
	('permit', 'M',   'married housing'),
	('permit', 'HM',  'housing manager'),
	('permit', 'HC',  'handicap'),
	('permit', 'EMT', 'emergency medical students'),
	('permit', 'CL',  'child lab'),
	('permit', 'V',   'visitor'),
	('permit', 'L',   'long term and overnight'),
	('permit', 'F',   'free'),
	('permit', 'Mo',  'motorcycle'),
	('permit', 'B',   'bicycle');


CREATE FUNCTION getPTK(typ TEXT)
	RETURNS INT DETERMINISTIC
	RETURN (SELECT lookupKey FROM parking.Lookup WHERE band = 'permit' AND term = typ);

INSERT INTO PermitType (permitTypeKey, rank, semesterCost, annualCost)
VALUES
	(getPTK('X'),   1,    0, 0),
	(getPTK('HC'), NULL, NULL, NULL),
	(getPTK('EMT'),NULL,  0, 0),
	(getPTK('ES'),  3,    0, 0),
	(getPTK('A'),   5,    0, 0),
	(getPTK('FN'),  8,    0, 0),
	(getPTK('FS'),  8,    0, 0),
	(getPTK('N'),   8,   30, 70),
	(getPTK('S'),   8,   20, 50),
	(getPTK('HM'), NULL,  0, 0),
	(getPTK('H'),  NULL, 30, NULL),
	(getPTK('M'),  NULL,  0, 0),
	(getPTK('C'),  NULL, 20, NULL),
	(getPTK('CL'), NULL,  0, 0),
	(getPTK('V'),  NULL,  0, 0),
	(getPTK('L'),  NULL, 20, 50),
	(getPTK('F'),  10,    0, 0),
	(getPTK('Mo'), NULL,  0, 0),
	(getPTK('B'),  NULL,  0, 0);

DROP FUNCTION getPTK;


INSERT INTO Lot (name, address, permitTypeKey)
VALUES
	('College Ave Lot', NULL, getPermitTypeKey('F')),
	('Fourth Ward Lot', NULL, getPermitTypeKey('C')),
	('2nd South Lot',   NULL, getPermitTypeKey('N')),
	('Stadium Lot',     NULL, getPermitTypeKey('N')),
	('Snow Lot',        NULL, getPermitTypeKey('A')),
	('Clarke Lot',      NULL, getPermitTypeKey('CL')),
	('Hinckley Lot',    NULL, getPermitTypeKey('N')),
	('2nd East Lot',    NULL, getPermitTypeKey('F')),
	('7th South Lot',   NULL, getPermitTypeKey('L'));


/*
INSERT INTO Rule (ruleKey, violationKey, operator, west, east)
VALUES
	(100, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='HM'),  NULL),
	(101, 1, 'ANY', NULL, 100),
	(102, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='B'),   101),
	(103, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='Mo'),  102),
	(104, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='F'),   103),
	(105, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='L'),   104),
	(106, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='V'),   105),
	(107, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='CL'),  106),
	(108, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='C'),   107),
	(109, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='M'),   108),
	(110, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='H'),   109),
	(111, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='HM'),  110),
	(112, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='S'),   111),
	(113, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='N'),   112),
	(114, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='FS'),  113),
	(115, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='FN'),  114),
	(116, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='EMT'), 115),
	(117, 1, 'ANY', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='HC'),  116),
	(118, 1, 'BETWEEN', 28000, 57600),
	(119, 1, 'PARKED', (SELECT permitTypeKey FROM vwPermitTypes WHERE term='A'), NULL),
	(120, 1, 'AND', 117, 118),
	(121, 1, 'AND', 119, 120);
*/
INSERT INTO Violation (name, first, increase)
VALUES
	('in Handicap', 75, 25),
	('in X', 50, 20),
	('in service', 30, 10),
	('permit in A', 20, 10),
	('permit in N or S', 15, 5),
	('no permit in A', 25, 10),
	('no permit in N or S', 15, 5);


INSERT INTO Owner (iNumber, given, family)
VALUES
	('142007000', 'Jasmine', 'Ottinger'),
	('502117111', 'Martin', 'Sousa');


INSERT INTO Vehicle (VIN, year, colorKey, bodyKey)
VALUES
	('174AB00000PPPZZ99', 2010, getColorKey('red'), getBodyKey('coupe')),
	('281AB00000PPPZZ99', 2009, getColorKey('silver'), getBodyKey('sedan')),
	('412AB00000PPPZZ99', 2012, getColorKey('blue'), getBodyKey('SUV'));


INSERT INTO OwnerHasVehicle (ownerKey, vehicleKey, dateEntered)
VALUES
	(getOwnerKey('142007000'), getVehicleKey('174AB00000PPPZZ99'), '2017-04-01'),
	(getOwnerKey('502117111'), getVehicleKey('281AB00000PPPZZ99'), '2016-09-01'),
	(getOwnerKey('502117111'), getVehicleKey('412AB00000PPPZZ99'), '2016-09-01');


INSERT INTO Plate (stateKey, number)
VALUES
	(getStateKey('UT'), 'ABC17320'),
	(getStateKey('ID'), '1M17320'),
	(getStateKey('ID'), '8B32AJ11');


INSERT INTO VehicleHasPlate (vehicleKey, plateKey, dateEntered)
VALUES
	(getVehicleKey('174AB00000PPPZZ99'), getPlateKey('UT', 'ABC17320'), '2017-04-01'),
	(getVehicleKey('281AB00000PPPZZ99'), getPlateKey('ID', '1M17320'),  '2016-09-01'),
	(getVehicleKey('412AB00000PPPZZ99'), getPlateKey('ID', '8B32AJ11'), '2016-09-01');


INSERT INTO Permit (ownerKey, permitTypeKey, issued, expires)
VALUES
	(getOwnerKey('142007000'), getPermitTypeKey('S'),
	 '2017-04-01', '2017-08-31'),
	(getOwnerKey('502117111'), getPermitTypeKey('A'),
	 '2016-09-01', '2019-08-31'),
	(getOwnerKey('502117111'), getPermitTypeKey('FN'),
	 '2016-09-01', '2019-08-31');


INSERT INTO Sighting (dateSighted, lotKey, plateKey, permitKey)
VALUES
	('2017-06-05', getLotKey('College Ave Lot'),
	 getPlateKey('UT', 'ABC17320'), getPermitKey('142007000', 'S')),
	('2017-06-05', getLotKey('College Ave Lot'),
	 getPlateKey('ID', '1M17320'), getPermitKey('502117111', 'A')),
	('2017-06-05', getLotKey('College Ave Lot'),
	 getPlateKey('ID', '8B32AJ11'), getPermitKey('502117111', 'FN')),

	('2017-06-06', getLotKey('2nd South Lot'),
	 getPlateKey('UT', 'ABC17320'), getPermitKey('142007000', 'S')), -- Cit
	('2017-06-06', getLotKey('2nd South Lot'),
	 getPlateKey('ID', '1M17320'), getPermitKey('502117111', 'A')),
	('2017-06-06', getLotKey('2nd South Lot'),
	 getPlateKey('ID', '8B32AJ11'), getPermitKey('502117111', 'FN')),

	('2017-06-07', getLotKey('Snow Lot'),
	 getPlateKey('UT', 'ABC17320'), getPermitKey('142007000', 'S')), -- Cit
	('2017-06-07', getLotKey('Snow Lot'),
	 getPlateKey('ID', '1M17320'), getPermitKey('502117111', 'A')),
	('2017-06-07', getLotKey('Snow Lot'),
	 getPlateKey('ID', '8B32AJ11'), getPermitKey('502117111', 'FN')), -- Cit

	('2017-06-08', getLotKey('Hinckley Lot'),
	 getPlateKey('UT', 'ABC17320'), getPermitKey('142007000', 'S')),
	('2017-06-08', getLotKey('Hinckley Lot'),
	 getPlateKey('ID', '1M17320'), getPermitKey('502117111', 'A')),
	('2017-06-08', getLotKey('Hinckley Lot'),
	 getPlateKey('ID', '8B32AJ11'), getPermitKey('502117111', 'FN')); -- Cit


INSERT INTO Citation (sightingKey, violationKey, cost)
VALUES
	(getSightingKey('2017-06-06', getLotKey('2nd South Lot'), getPlateKey('UT', 'ABC17320')),
	 getViolationKey('permit in N or S'),
	 getCitationCost(
		getSightingKey('2017-06-06', getLotKey('2nd South Lot'), getPlateKey('UT', 'ABC17320')),
		getViolationKey('permit in N or S'))),

	(getSightingKey('2017-06-07', getLotKey('Snow Lot'), getPlateKey('UT', 'ABC17320')),
	 getViolationKey('permit in A'),
	 getCitationCost(
		getSightingKey('2017-06-07', getLotKey('Snow Lot'), getPlateKey('UT', 'ABC17320')),
		getViolationKey('permit in A'))),

	(getSightingKey('2017-06-07', getLotKey('Snow Lot'), getPlateKey('ID', '8B32AJ11')),
	 getViolationKey('permit in A'),
	 getCitationCost(
		getSightingKey('2017-06-07', getLotKey('Snow Lot'), getPlateKey('ID', '8B32AJ11')),
		getViolationKey('permit in A'))),

	(getSightingKey('2017-06-08', getLotKey('Hinckley Lot'), getPlateKey('ID', '8B32AJ11')),
	 getViolationKey('permit in A'),
	 getCitationCost(
		getSightingKey('2017-06-08', getLotKey('Hinckley Lot'), getPlateKey('ID', '8B32AJ11')),
		getViolationKey('permit in A')));

-- STR_TO_DATE('2017-06-22 10:00', '%Y-%m-%d %H:%i')

COMMIT;
