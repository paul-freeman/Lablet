/*
 * Copyright 2014.
 * Distributed under the terms of the GPLv3 License.
 *
 * Authors:
 *      Clemens Zeidler <czei002@aucklanduni.ac.nz>
 */
package nz.ac.auckland.lablet.experiment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.IOException;


public interface IExperimentSensor {
    public interface IListener {
        public void onStartPreview();
        public void onStopPreview();
        public void onStartRecording();
        public void onStopRecording();
        public void onStartPlayback();
        public void onStopPlayback();
        public void onSettingsChanged();
    }

    public String getSensorName();

    public View createExperimentView(Context context);

    /**
     * Prepares a option menu for the experiment.
     *
     * @param menuItem the menu item for the option menu
     * @return false if there is no option menu
     */
    public boolean onPrepareOptionsMenu(MenuItem menuItem);

    public void onSaveInstanceState(Bundle outState);
    public void onRestoreInstanceState(Bundle savedInstanceState);

    public ExperimentRun getExperimentRun();
    public void setExperimentRun(ExperimentRun experimentRun);

    public void init(Activity activity);
    public void destroy();

    /**
     * Finishes the experiment by either saving or discarding the data.
     *
     * We allow a sensor to produce more than one type of data. For example, an external sensor could act as a
     * thermometer and a barometer at the same time. For that reason the sensor is responsible to create one or more
     * directories in the storageBaseDir to store its data.
     *
     * @param saveData flag that indicates if data should be saved
     * @param storageBaseDir the data should be saved into a sub directory of storageBaseDir
     * @throws IOException
     */
    public void finishExperiment(boolean saveData, File storageBaseDir) throws IOException;
    public boolean dataTaken();

    public void startPreview();
    public void stopPreview();
    public void startRecording() throws Exception;
    /**
     * Stops the recording.
     *
     * @return true if some data has been taken otherwise false
     */
    public boolean stopRecording();
    public void startPlayback();
    public void stopPlayback();

    public ISensorData getExperimentData();
}
