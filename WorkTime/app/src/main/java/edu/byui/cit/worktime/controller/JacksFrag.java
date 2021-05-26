package edu.byui.cit.worktime.controller;

import android.app.Activity;

import androidx.fragment.app.Fragment;

public class JacksFrag extends Fragment {
    public MainActivity getMainActivity() {
        MainActivity activity = (MainActivity)getActivity();
        assert activity != null;
        return activity;

    }
}
