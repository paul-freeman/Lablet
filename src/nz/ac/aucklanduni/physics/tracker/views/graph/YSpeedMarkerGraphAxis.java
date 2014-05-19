/*
 * Copyright 2013-2014.
 * Distributed under the terms of the GPLv3 License.
 *
 * Authors:
 *      Clemens Zeidler <czei002@aucklanduni.ac.nz>
 */
package nz.ac.aucklanduni.physics.tracker.views.graph;


import nz.ac.aucklanduni.physics.tracker.experiment.Experiment;
import nz.ac.aucklanduni.physics.tracker.experiment.ExperimentAnalysis;
import nz.ac.aucklanduni.physics.tracker.experiment.MarkersDataModel;

public class YSpeedMarkerGraphAxis extends MarkerGraphAxis {
    @Override
    public int size() {
        return getData().getMarkerCount() - 1;
    }

    @Override
    public Number getValue(int index) {
        ExperimentAnalysis experimentAnalysis = getExperimentAnalysis();
        MarkersDataModel data = getData();

        Experiment experiment = experimentAnalysis.getExperiment();
        float deltaX = data.getCalibratedMarkerPositionAt(index + 1).y - data.getCalibratedMarkerPositionAt(index).y;
        float deltaT = experiment.getRunValueAt(index + 1) - experiment.getRunValueAt(index);
        if (experimentAnalysis.getExperiment().getRunValueUnitPrefix().equals("m"))
            deltaT /= 1000;
        return deltaX / deltaT;
    }

    @Override
    public String getLabel() {
        ExperimentAnalysis experimentAnalysis = getExperimentAnalysis();
        return "velocity [" + experimentAnalysis.getXUnit() + "/"
                + experimentAnalysis.getExperiment().getRunValueBaseUnit() + "]";
    }

    @Override
    public Number getMinRange() {
        return 3;
    }
}
