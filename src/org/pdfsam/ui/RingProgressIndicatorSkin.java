/* 
 * Copyright (c) 2014, Andrea Vacondio
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package org.pdfsam.ui;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Skin of the ring progress indicator where an arc grows and by the progress value up to 100% where the arc becomes a ring.
 * 
 * @author Andrea Vacondio
 * @author  Camille Bauce
 *
 */
public class RingProgressIndicatorSkin implements Skin<RingProgressIndicator> {

    private final RingProgressIndicator indicator;
    private final Label percentLabel = new Label();
    private final Circle innerCircle = new Circle();
    private final Circle outerCircle = new Circle();
    private final StackPane container = new StackPane();
    private final Arc fillerArc = new Arc();
    private final Arc fillerArcOver100 = new Arc();
    private final RotateTransition transition = new RotateTransition(Duration.millis(2000), fillerArc);

    public RingProgressIndicatorSkin(final RingProgressIndicator indicator) {
        this.indicator = indicator;
        initContainer(indicator);

        initFillerArcs();

        container.widthProperty().addListener((o, oldVal, newVal) -> {
            fillerArc.setCenterX(newVal.intValue() / 2);
            fillerArcOver100.setCenterX(newVal.intValue() / 2);
        });
        container.heightProperty().addListener((o, oldVal, newVal) -> {
            fillerArc.setCenterY(newVal.intValue() / 2);
            fillerArcOver100.setCenterY(newVal.intValue() / 2);
        });

        updateInnerCircle(indicator.getProgress());
        outerCircle.getStyleClass().add("ringindicator-outer-circle-secondary");
        updateRadii();

        this.indicator.indeterminateProperty().addListener((o, oldVal, newVal) -> {
            initIndeterminate(newVal);
        });
        this.indicator.progressProperty().addListener((o, oldVal, newVal) -> {
            int newValOver100 = 0;
            int newValUnder100 = newVal.intValue();
            if(newVal.intValue() > 100){
                newValOver100 = newVal.intValue()%100;
                newValUnder100 = newVal.intValue() - newValOver100;
            }
            if (newVal.intValue() >= 0) {
                updateLabel(newVal.intValue());
                updateInnerCircle(newVal.intValue());
                fillerArc.setLength(newValUnder100 * -3.6);
                fillerArcOver100.setLength(newValOver100 * -3.6);
            }
        });
        this.indicator.ringWidthProperty().addListener((o, oldVal, newVal) -> {
            updateRadii();
        });
        innerCircle.strokeWidthProperty().addListener((e) -> {
            updateRadii();
        });
        innerCircle.radiusProperty().addListener((e) -> {
            updateRadii();
        });
        initTransition();
        initIndeterminate(indicator.isIndeterminate());
        updateLabel(indicator.getProgress());
        indicator.visibleProperty().addListener((o, oldVal, newVal) -> {
            if (newVal && this.indicator.isIndeterminate()) {
                transition.play();
            } else {
                transition.pause();
            }
        });
        container.getStyleClass().add("container");
        container.getChildren().addAll(fillerArc,fillerArcOver100, outerCircle, innerCircle, percentLabel);
    }

    private void setProgressLabel(int value) {
        if (value >= 0) {
            percentLabel.setText(String.format("%d%%", value));
        }
    }

    private void initTransition() {
        transition.setAutoReverse(false);
        transition.setCycleCount(Animation.INDEFINITE);
        transition.setDelay(Duration.ZERO);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setByAngle(360);
    }

    private void initFillerArcs() {
        int newValOver100 = 0;
        int newValUnder100 = indicator.getProgress();
        if(indicator.getProgress() > 100){
            newValOver100 = indicator.getProgress()%100;
            newValUnder100 =indicator.getProgress() - newValOver100;
        }

        fillerArc.setManaged(false);
        fillerArc.getStyleClass().add("ringindicator-filler");
        fillerArc.setStartAngle(90);
        fillerArc.setLength(newValUnder100 * -3.6);

        fillerArcOver100.setManaged(false);
        fillerArcOver100.getStyleClass().add("ringindicator-over100-filler");
        fillerArcOver100.setStartAngle(90);
        fillerArcOver100.setLength(newValOver100 * -3.6);
    }

    private void initContainer(final RingProgressIndicator indicator) {
        container.getStylesheets().addAll(indicator.getStylesheets());
        container.getStyleClass().addAll("circleindicator-container");
        container.setMaxHeight(Region.USE_PREF_SIZE);
        container.setMaxWidth(Region.USE_PREF_SIZE);
    }

    private void updateRadii() {
        double ringWidth = indicator.getRingWidth();
        double innerCircleHalfStrokeWidth = innerCircle.getStrokeWidth() / 2;
        double innerCircleRadius = indicator.getInnerCircleRadius();
        outerCircle.setRadius(innerCircleRadius + innerCircleHalfStrokeWidth + ringWidth);
        fillerArc.setRadiusY(innerCircleRadius + innerCircleHalfStrokeWidth - 1 + (ringWidth / 2));
        fillerArc.setRadiusX(innerCircleRadius + innerCircleHalfStrokeWidth - 1 + (ringWidth / 2));
        fillerArc.setStrokeWidth(ringWidth);
        fillerArcOver100.setRadiusY(innerCircleRadius + innerCircleHalfStrokeWidth - 1 + (ringWidth / 2));
        fillerArcOver100.setRadiusX(innerCircleRadius + innerCircleHalfStrokeWidth - 1 + (ringWidth / 2));
        fillerArcOver100.setStrokeWidth(ringWidth);
        innerCircle.setRadius(innerCircleRadius);
    }

    private void updateLabel(int value) {
        if(value > 100){
            percentLabel.getStyleClass().add("circleindicator100-label");
            percentLabel.getStyleClass().remove("circleindicator-label");
        }
        else {
            percentLabel.getStyleClass().add("circleindicator-label");
            percentLabel.getStyleClass().remove("circleindicator000-label");
        }
        setProgressLabel(value);
    }

    private void updateInnerCircle(int value) {
        if(value > 100){
            innerCircle.getStyleClass().add("ringindicator-over100-inner-circle");
            innerCircle.getStyleClass().remove("ringindicator-inner-circle");
        }
        else {
            innerCircle.getStyleClass().add("ringindicator-inner-circle");
            innerCircle.getStyleClass().remove("ringindicator-over100-inner-circle");
        }
    }



    private void initIndeterminate(boolean newVal) {
        percentLabel.setVisible(!newVal);
        if (newVal) {
            fillerArc.setLength(360);
            fillerArc.getStyleClass().add("indeterminate");
            if (indicator.isVisible()) {
                transition.play();
            }
        } else {
            fillerArc.getStyleClass().remove("indeterminate");
            fillerArc.setRotate(0);
            fillerArcOver100.setRotate(0);
            transition.stop();
        }
    }

    @Override
    public RingProgressIndicator getSkinnable() {
        return indicator;
    }

    @Override
    public Node getNode() {
        return container;
    }

    @Override
    public void dispose() {
        transition.stop();
    }

}
