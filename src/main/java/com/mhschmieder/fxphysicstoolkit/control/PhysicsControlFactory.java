/**
 * MIT License
 *
 * Copyright (c) 2020, 2022 Mark Schmieder
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is part of the FxPhysicsToolkit Library
 *
 * You should have received a copy of the MIT License along with the
 * FxPhysicsToolkit Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/fxphysicstoolkit
 */
package com.mhschmieder.fxphysicstoolkit.control;

import com.mhschmieder.commonstoolkit.util.ClientProperties;
import com.mhschmieder.physicstoolkit.AngleUnit;

/**
 * This is a factory for generating customized controls for physics-based
 * concepts. It is a way of avoiding class derivation where that is not
 * necessary for altering the default behavior of the core JavaFX controls --
 * especially for stuff like spinners.
 * <p>
 * One of its roles is to enforce preferred style guidelines and common
 * behavior.
 *
 * @version 0.1
 *
 * @author Mark Schmieder
 */
public class PhysicsControlFactory {

    /**
     * The default constructor is disabled, as this is a static factory class.
     */
    private PhysicsControlFactory() {}

    // Helper method to get an Angle Editor, standalone or paired.
    public static final AngleEditor makeAngleEditor( final ClientProperties clientProperties,
                                                     final String tooltipText,
                                                     final double minimumValue,
                                                     final double maximumValue,
                                                     final double initialValue ) {
        final AngleEditor angleEditor = makeAngleEditor( clientProperties,
                                                         tooltipText,
                                                         AngleUnit.DEGREES.toPresentationString(),
                                                         minimumValue,
                                                         maximumValue,
                                                         initialValue );

        return angleEditor;
    }

    // Helper method to get an Angle Editor, standalone or paired.
    public static final AngleEditor makeAngleEditor( final ClientProperties clientProperties,
                                                     final String tooltipText,
                                                     final int minFractionDigitsFormat,
                                                     final int maxFractionDigitsFormat,
                                                     final int minFractionDigitsParse,
                                                     final int maxFractionDigitsParse,
                                                     final String measurementUnit,
                                                     final double minimumValue,
                                                     final double maximumValue,
                                                     final double initialValue ) {
        // Get the current value and format it as initial text.
        final String initialText = Double.toString( initialValue );

        final AngleEditor angleEditor = new AngleEditor( clientProperties,
                                                         initialText,
                                                         tooltipText,
                                                         minFractionDigitsFormat,
                                                         maxFractionDigitsFormat,
                                                         minFractionDigitsParse,
                                                         maxFractionDigitsParse,
                                                         minimumValue,
                                                         maximumValue,
                                                         initialValue );

        angleEditor.setMeasurementUnitString( measurementUnit );

        return angleEditor;
    }

    // Helper method to get an Angle Editor, standalone or paired.
    public static final AngleEditor makeAngleEditor( final ClientProperties clientProperties,
                                                     final String tooltipText,
                                                     final String measurementUnit,
                                                     final double minimumValue,
                                                     final double maximumValue,
                                                     final double initialValue ) {
        final AngleEditor angleEditor = makeAngleEditor( clientProperties,
                                                         tooltipText,
                                                         0,
                                                         2,
                                                         0,
                                                         10,
                                                         measurementUnit,
                                                         minimumValue,
                                                         maximumValue,
                                                         initialValue );

        return angleEditor;
    }

    // Helper method to get an Angle Editor to pair with a slider.
    public static final AngleEditor makeAngleSliderEditor( final ClientProperties clientProperties,
                                                           final AngleSlider angleSlider ) {
        final AngleEditor angleEditor = makeAngleSliderEditor( clientProperties,
                                                               angleSlider,
                                                               0,
                                                               2,
                                                               0,
                                                               10 );

        return angleEditor;
    }

    // Helper method to get an Angle Editor to pair with a slider.
    public static final AngleEditor makeAngleSliderEditor( final ClientProperties clientProperties,
                                                           final AngleSlider angleSlider,
                                                           final int minFractionDigitsFormat,
                                                           final int maxFractionDigitsFormat,
                                                           final int minFractionDigitsParse,
                                                           final int maxFractionDigitsParse ) {
        // Use the current slider value and limits to set the number editor.
        final AngleEditor angleEditor = makeAngleEditor( clientProperties,
                                                         null,
                                                         minFractionDigitsFormat,
                                                         maxFractionDigitsFormat,
                                                         minFractionDigitsParse,
                                                         maxFractionDigitsParse,
                                                         angleSlider.getMeasurementUnitString(),
                                                         angleSlider.getMin(),
                                                         angleSlider.getMax(),
                                                         angleSlider.getValue() );

        return angleEditor;
    }

    // Helper method to get a custom Temperature Editor.
    public static final TemperatureEditor makeTemperatureEditor( final ClientProperties clientProperties ) {
        // Format the default Temperature value as the initial text.
        final double initialValue = TemperatureSlider.INITIAL_TEMPERATURE_KELVIN_DEFAULT;
        final String initialText = Double.toString( initialValue );

        final TemperatureEditor temperatureEditor =
                                                  new TemperatureEditor( clientProperties,
                                                                         initialText,
                                                                         null,
                                                                         TemperatureSlider.MINIMUM_TEMPERATURE_KELVIN_DEFAULT,
                                                                         TemperatureSlider.MAXIMUM_TEMPERATURE_KELVIN_DEFAULT,
                                                                         initialValue );

        return temperatureEditor;
    }

    // Helper method to get a custom Pressure Editor.
    public static final PressureEditor makePressureEditor( final ClientProperties clientProperties ) {
        // Format the default Pressure value as the initial text.
        final double initialValue = PressureSlider.INITIAL_PRESSURE_PASCALS_DEFAULT;
        final String initialText = Double.toString( initialValue );

        final PressureEditor pressureEditor =
                                            new PressureEditor( clientProperties,
                                                                initialText,
                                                                null,
                                                                PressureSlider.MINIMUM_PRESSURE_PASCALS_DEFAULT,
                                                                PressureSlider.MAXIMUM_PRESSURE_PASCALS_DEFAULT,
                                                                initialValue );

        return pressureEditor;
    }

    // Helper method to get a humidity editor to pair with a slider.
    public static final HumidityEditor makeHumiditySliderEditor( final ClientProperties clientProperties,
                                                                 final HumiditySlider humiditySlider ) {
        // Get the current slider value and format it as initial text.
        final double initialValue = HumiditySlider.INITIAL_RELATIVE_HUMIDITY_DEFAULT;
        final String initialText = Double.toString( initialValue );

        final HumidityEditor humidityEditor =
                                            new HumidityEditor( clientProperties,
                                                                initialText,
                                                                null,
                                                                HumiditySlider.MINIMUM_RELATIVE_HUMIDITY_DEFAULT,
                                                                HumiditySlider.MAXIMUM_RELATIVE_HUMIDITY_DEFAULT,
                                                                initialValue );

        final String measurementUnitString = humiditySlider.getMeasurementUnitString();
        humidityEditor.setMeasurementUnitString( measurementUnitString );

        return humidityEditor;
    }

}
