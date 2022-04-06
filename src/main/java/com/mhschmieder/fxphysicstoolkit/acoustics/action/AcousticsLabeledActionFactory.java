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
package com.mhschmieder.fxphysicstoolkit.acoustics.action;

import java.util.Collection;

import org.controlsfx.control.action.Action;

import com.mhschmieder.commonstoolkit.util.ClientProperties;
import com.mhschmieder.fxguitoolkit.action.ActionFactory;
import com.mhschmieder.fxguitoolkit.action.XAction;
import com.mhschmieder.fxguitoolkit.action.XActionGroup;

/**
 * This is a utility class for making labeled actions for Acoustics.
 * <p>
 * TODO: Move the icons into the resources folder and reference correctly.
 */
public class AcousticsLabeledActionFactory {

    // TODO: Review whether this is still correct, now that we have to package
    // all non-Java resource files in a separate hierarchy from the Java package
    // for the source code.
    @SuppressWarnings("nls") public static final String BUNDLE_NAME =
                                                                    "com.mhschmieder.fxphysicstoolkit.acoustics.action.AcousticsActionLabels";

    /**
     * The default constructor is disabled, as this is a static factory class.
     */
    private AcousticsLabeledActionFactory() {}

    @SuppressWarnings("nls")
    public static XActionGroup getSplPaletteChoiceGroup( final ClientProperties clientProperties,
                                                         final SplPaletteChoices splPaletteChoices ) {
        final Collection< Action > splPaletteChoiceCollection = splPaletteChoices
                .getSplPaletteChoiceCollection();

        final XActionGroup splPaletteChoiceGroup = ActionFactory
                .makeChoiceGroup( clientProperties,
                                  splPaletteChoiceCollection,
                                  BUNDLE_NAME,
                                  "splPalette",
                                  "/com/led24/icons/Palette16.png" );

        return splPaletteChoiceGroup;
    }

    @SuppressWarnings("nls")
    public static XAction getSplPaletteColor1dbChoice( final ClientProperties clientProperties ) {
        return ActionFactory
                .makeChoice( clientProperties, BUNDLE_NAME, "splPalette", "color1db", null, true );
    }

    @SuppressWarnings("nls")
    public static XAction getSplPaletteColor2dbChoice( final ClientProperties clientProperties ) {
        return ActionFactory
                .makeChoice( clientProperties, BUNDLE_NAME, "splPalette", "color2db", null, true );
    }

    @SuppressWarnings("nls")
    public static XAction getSplPaletteColor3dbChoice( final ClientProperties clientProperties ) {
        return ActionFactory
                .makeChoice( clientProperties, BUNDLE_NAME, "splPalette", "color3db", null, true );
    }

    @SuppressWarnings("nls")
    public static XAction getSplPaletteColors256Choice( final ClientProperties clientProperties ) {
        return ActionFactory
                .makeChoice( clientProperties, BUNDLE_NAME, "splPalette", "colors256", null, true );
    }

    @SuppressWarnings("nls")
    public static XAction getSplPaletteColors64Choice( final ClientProperties clientProperties ) {
        return ActionFactory
                .makeChoice( clientProperties, BUNDLE_NAME, "splPalette", "colors64", null, true );
    }

    @SuppressWarnings("nls")
    public static XAction getSettingsSplRangeAction( final ClientProperties clientProperties ) {
        return ActionFactory.makeAction( clientProperties,
                                         BUNDLE_NAME,
                                         "settings",
                                         "splRange",
                                         "/com/acoustics/resources/SplRange16.png" );
    }

    @SuppressWarnings("nls")
    public static XAction getTestDitheringAmountAction( final ClientProperties clientProperties ) {
        return ActionFactory.makeAction( clientProperties,
                                         BUNDLE_NAME,
                                         "test",
                                         "ditheringAmount",
                                         "/com/yusukeKamiyamane/icons/ImageBlur16.png" );
    }

    @SuppressWarnings("nls")
    public static XAction getSmoothingChoice( final ClientProperties clientProperties,
                                              final String itemName ) {
        return ActionFactory
                .makeChoice( clientProperties, BUNDLE_NAME, "smoothing", itemName, null, true );
    }

    @SuppressWarnings("nls")
    public static XAction getSmoothingNarrowChoice( final ClientProperties clientProperties ) {
        return getSmoothingChoice( clientProperties, "narrow" );
    }

    @SuppressWarnings("nls")
    public static XAction getSmoothingSixthOctaveChoice( final ClientProperties clientProperties ) {
        return getSmoothingChoice( clientProperties, "sixthOctave" );
    }

    @SuppressWarnings("nls")
    public static XAction getSmoothingThirdOctaveChoice( final ClientProperties clientProperties ) {
        return getSmoothingChoice( clientProperties, "thirdOctave" );
    }

}
