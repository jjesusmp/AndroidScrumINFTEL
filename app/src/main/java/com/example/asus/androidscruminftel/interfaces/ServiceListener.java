package com.example.asus.androidscruminftel.interfaces;

import android.util.Pair;

import com.example.asus.androidscruminftel.model.ProjectChat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 08/04/2016.
 */
public interface ServiceListener {
    public void onServiceResponse(ArrayList<ProjectChat> response);
}
