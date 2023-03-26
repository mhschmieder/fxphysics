/**
 * MIT License
 *
 * Copyright (c) 2020, 2023 Mark Schmieder
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
package com.mhschmieder.fxphysicstoolkit.stage;

import java.io.File;
import java.util.prefs.Preferences;

import com.mhschmieder.commonstoolkit.branding.ProductBranding;
import com.mhschmieder.commonstoolkit.io.FileUtilities;
import com.mhschmieder.commonstoolkit.util.ClientProperties;
import com.mhschmieder.fxguitoolkit.ScrollingSensitivity;
import com.mhschmieder.fxguitoolkit.action.BackgroundColorChoices;
import com.mhschmieder.fxguitoolkit.stage.XStage;
import com.mhschmieder.fxphysicstoolkit.action.NaturalEnvironmentActions;
import com.mhschmieder.fxphysicstoolkit.control.NaturalEnvironmentToolBar;
import com.mhschmieder.fxphysicstoolkit.control.PhysicsMenuFactory;
import com.mhschmieder.fxphysicstoolkit.layout.NaturalEnvironmentPane;
import com.mhschmieder.fxphysicstoolkit.model.NaturalEnvironment;
import com.mhschmieder.physicstoolkit.DistanceUnit;
import com.mhschmieder.physicstoolkit.PressureUnit;
import com.mhschmieder.physicstoolkit.TemperatureUnit;

import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;

public final class NaturalEnvironmentStage extends XStage {

    public static final String       NATURAL_ENVIRONMENT_FRAME_TITLE_DEFAULT  =
                                                                             "Natural Environment"; //$NON-NLS-1$

    // Default window locations and dimensions.
    public static final int          NATURAL_ENVIRONMENT_STAGE_X_DEFAULT      = 20;
    public static final int          NATURAL_ENVIRONMENT_STAGE_Y_DEFAULT      = 20;
    private static final int         NATURAL_ENVIRONMENT_STAGE_WIDTH_DEFAULT  = 640;
    private static final int         NATURAL_ENVIRONMENT_STAGE_HEIGHT_DEFAULT = 400;

    // Declare the actions.
    public NaturalEnvironmentActions _actions;

    // Declare the main tool bar.
    public NaturalEnvironmentToolBar _toolBar;

    // Declare the main content pane.
    public NaturalEnvironmentPane    _naturalEnvironmentPane;

    // Cache a reference to the global Natural Environment.
    public NaturalEnvironment        _naturalEnvironment;
    
    // Flag for whether vector graphics are supported.
    protected final boolean _vectorGraphicsSupported;
    
    // Flag for whether Use Air Attenuation should be initialized to on or off.
    protected final boolean _initialUseAirAttenuation;

    @SuppressWarnings("nls")
    public NaturalEnvironmentStage( final ProductBranding productBranding,
                                    final ClientProperties pClientProperties,
                                    final boolean vectorGraphicsSupported,
                                    final boolean initialUseAirAttenuation ) {
        // Always call the superclass constructor first!
        super( NATURAL_ENVIRONMENT_FRAME_TITLE_DEFAULT,
               "naturalEnvironment2",
               true,
               true,
               productBranding,
               pClientProperties );
        
        _vectorGraphicsSupported = vectorGraphicsSupported;
        _initialUseAirAttenuation = initialUseAirAttenuation;
        
        try {
            initStage( true );
        }
        catch ( final Exception ex ) {
            ex.printStackTrace();
        }
    }

    // Add all of the relevant action handlers.
    @Override
    protected void addActionHandlers() {
        // Load the action handlers for the "File" actions.
        _actions._fileActions._closeWindowAction.setEventHandler( evt -> doCloseWindow() );
        _actions._fileActions._pageSetupAction.setEventHandler( evt -> doPageSetup() );
        _actions._fileActions._printAction.setEventHandler( evt -> doPrint() );

        // Load the action handlers for the "Export" actions.
        _actions._fileActions._exportActions._exportImageGraphicsAction
                .setEventHandler( evt -> doExportImageGraphics() );
        _actions._fileActions._exportActions._exportVectorGraphicsAction
                .setEventHandler( evt -> doExportVectorGraphics() );

        // Load the action handlers for the "Background Color" choices.
        addBackgroundColorChoiceHandlers( _actions._settingsActions._backgroundColorChoices );

        // Load the action handlers for the "Window Size" actions.
        addWindowSizeActionHandlers( _actions._settingsActions._windowSizeActions );

        // Load the action handlers for the "Tools" actions.
        // NOTE: These are registered at the top-most level of the application.

        // Load the action handler for the "Reset" action.
        _actions._resetAction.setEventHandler( evt -> doReset() );
    }

    // Add the Tool Bar's event listeners.
    // TODO: Use appropriate methodology to add an action linked to both
    // the toolbar buttons and their associated menu items, so that when one
    // is disabled the other is as well. Is this already true of what we do?
    @Override
    protected void addToolBarListeners() {
        // Detect the ENTER key while the Use Air Attenuation Check Box has
        // focus, and use it to toggle the state (standard expected behavior).
        _toolBar._useAirAttenuationCheckBox.setOnKeyReleased( keyEvent -> {
            final KeyCombination keyCombo = new KeyCodeCombination( KeyCode.ENTER );
            if ( keyCombo.match( keyEvent ) ) {
                // Trigger the Use Air Attenuation Toggle action.
                doUseAirAttenuationToggle();

                // Consume the ENTER key so it doesn't get processed twice.
                keyEvent.consume();
            }
        } );

        // Detect the ENTER key while the Reset Button has focus, and use it to
        // trigger its action (standard expected behavior).
        _toolBar._resetButton.setOnKeyReleased( keyEvent -> {
            final KeyCombination keyCombo = new KeyCodeCombination( KeyCode.ENTER );
            if ( keyCombo.match( keyEvent ) ) {
                // Trigger the Reset action.
                doReset();

                // Consume the ENTER key so it doesn't get processed
                // twice.
                keyEvent.consume();
            }
        } );
    }

    private void bindProperties() {
        // The Use Air Attenuation flag is a simple boolean so can be
        // bi-directionally bound to its corresponding check box.
        _toolBar._useAirAttenuationCheckBox.selectedProperty()
                .bindBidirectional( _naturalEnvironment.airAttenuationAppliedProperty() );
    }

    public void doExportImageGraphics() {
        // Switch on export context, so we know which type of data and format to
        // save.
        final String imageCategory = "Natural Environment"; //$NON-NLS-1$
        fileExportImageGraphics( imageCategory );
    }

    public void doExportVectorGraphics() {
        // Switch on export context, so we know which type of data and format to
        // save.
        final String graphicsCategory = "Natural Environment"; //$NON-NLS-1$
        fileExportVectorGraphics( graphicsCategory );
    }

    protected void doReset() {
        reset();
    }

    protected void doUseAirAttenuationToggle() {
        // Toggle the state of the Use Air Attenuation Check Box.
        // NOTE: As this is tab-focus related, it must be performed on the
        // Check Box itself rather than on the associated action container.
        _toolBar._useAirAttenuationCheckBox
                .setSelected( !_toolBar._useAirAttenuationCheckBox.isSelected() );
    }

    @SuppressWarnings("nls")
    protected void initStage( final boolean resizable ) {
        // First have the superclass initialize its content.
        initStage( "/com/meyersound/analysis/resources/NaturalEnvironment16.png",
                   NATURAL_ENVIRONMENT_STAGE_WIDTH_DEFAULT,
                   NATURAL_ENVIRONMENT_STAGE_HEIGHT_DEFAULT,
                   resizable );
    }

    // Load the relevant actions for this Stage.
    @Override
    protected void loadActions() {
        // Make all of the actions.
        _actions = new NaturalEnvironmentActions( clientProperties, _vectorGraphicsSupported );
    }

    @Override
    protected Node loadContent() {
        // Instantiate and return the custom Content Node.
        _naturalEnvironmentPane = new NaturalEnvironmentPane( clientProperties );
        return _naturalEnvironmentPane;
    }

    // Add the Menu Bar for this Stage.
    @Override
    protected MenuBar loadMenuBar() {
        // Build the Menu Bar for this Stage.
        final MenuBar menuBar = PhysicsMenuFactory.getNaturalEnvironmentMenuBar( clientProperties,
                                                                                  _actions );

        // Return the Menu Bar so the superclass can use it.
        return menuBar;
    }

    // Load all of the User Preferences for this Stage.
    // TODO: Make a class with get/set methods for user preferences, a la
    // Listing 3.3 on p. 37 of "More Java Pitfalls" (Wiley), and including
    // static default values for better modularity.
    @SuppressWarnings("nls")
    @Override
    public void loadPreferences() {
        // Get the user node for this package/class, so that we get the
        // preferences specific to this frame and user.
        final Preferences prefs = Preferences.userNodeForPackage( getClass() );

        // Create and set the visualization parameters.
        final String backgroundColor = prefs
                .get( "backgroundColor", BackgroundColorChoices.DEFAULT_BACKGROUND_COLOR_NAME );

        // Load the Default Directory from User Preferences.
        final File defaultDirectory = FileUtilities.loadDefaultDirectoryPreferences( prefs );

        // Forward the preferences data from the stored preferences to the
        // common preferences handler.
        updatePreferences( backgroundColor, defaultDirectory );
    }

    // Add the Tool Bar for this Stage.
    @Override
    public ToolBar loadToolBar() {
        // Build the Tool Bar for this Stage.
        _toolBar = new NaturalEnvironmentToolBar( clientProperties, _actions );

        // Return the Tool Bar so the superclass can use it.
        return _toolBar;
    }

    // Reset all fields to the default values, regardless of state.
    // NOTE: This is done from the view vs. the model, as there may be more
    // than one component per property (e.g. the radio buttons for altitude, as
    // part of atmospheric pressure as an alternate specification of pressure).
    @Override
    protected void reset() {
        _toolBar._useAirAttenuationCheckBox.setSelected( _initialUseAirAttenuation );

        // Forward this method to the Natural Environment Pane.
        _naturalEnvironmentPane.reset();
    }

    // Save all of the non-login User Preferences for this Stage.
    // TODO: Make a class with get/set methods for user preferences, a la
    // Listing 3.3 on p. 37 of "More Java Pitfalls" (Wiley).
    @SuppressWarnings("nls")
    @Override
    public void savePreferences() {
        // Get the user node for this package/class, so that we get the
        // preferences specific to this frame and user.
        final Preferences prefs = Preferences.userNodeForPackage( getClass() );

        final String backgroundColor = _actions.getSelectedBackgroundColorName();
        prefs.put( "backgroundColor", backgroundColor );

        // Save the Default Directory to User Preferences.
        FileUtilities.saveDefaultDirectoryPreferences( _defaultDirectory, prefs );
    }

    @Override
    public void setForegroundFromBackground( final Color backColor ) {
        // Take care of general styling first, as that also loads shared
        // variables.
        super.setForegroundFromBackground( backColor );

        // Forward this reference to the Natural Environment Pane.
        _naturalEnvironmentPane.setForegroundFromBackground( backColor );
    }

    public void setGesturesEnabled( final boolean gesturesEnabled ) {
        // Forward this method to the Natural Environment Pane.
        _naturalEnvironmentPane.setGesturesEnabled( gesturesEnabled );
    }

    // Set and propagate the Natural Environment reference.
    // NOTE: This should be done only once, to avoid breaking bindings.
    public void setNaturalEnvironment( final NaturalEnvironment pNaturalEnvironment ) {
        // Cache the Natural Environment reference.
        _naturalEnvironment = pNaturalEnvironment;

        // Forward this reference to the Natural Environment Pane.
        _naturalEnvironmentPane.setNaturalEnvironment( _naturalEnvironment );

        // Bind the data model to the respective GUI components.
        bindProperties();
    }

    /**
     * Set the new Scrolling Sensitivity for all of the sliders.
     *
     * @param scrollingSensitivity
     *            The sensitivity of the mouse scroll wheel
     */
    public void setScrollingSensitivity( final ScrollingSensitivity scrollingSensitivity ) {
        // Forward this reference to the Natural Environment Pane.
        _naturalEnvironmentPane.setScrollingSensitivity( scrollingSensitivity );
    }

    public void toggleGestures() {
        // Forward this method to the Natural Environment Pane.
        _naturalEnvironmentPane.toggleGestures();
    }

    /**
     * Propagate the new Distance Unit to the relevant subcomponents.
     */
    public void updateDistanceUnit( final DistanceUnit distanceUnit ) {
        // Forward this method to the Natural Environment Pane.
        _naturalEnvironmentPane.updateDistanceUnit( distanceUnit );
    }

    // Update all of the User Preferences for this Stage.
    // TODO: Make a preferences object instead, with get/set methods, which can
    // be set from HTML, XML, or stored user preferences?
    private void updatePreferences( final String backgroundColorName,
                                    final File defaultDirectory ) {
        // Set the background color for most layout content.
        final Color backgroundColor = _actions.selectBackgroundColor( backgroundColorName );
        setForegroundFromBackground( backgroundColor );

        // Reset the default directory for local file operations.
        setDefaultDirectory( defaultDirectory );
    }

    /**
     * Propagate the new Pressure Unit to the relevant subcomponents.
     */
    public void updatePressureUnit( final PressureUnit pressureUnit ) {
        // Forward this method to the Natural Environment Pane.
        _naturalEnvironmentPane.updatePressureUnit( pressureUnit );
    }

    /**
     * Propagate the new Temperature Unit to the relevant subcomponents.
     */
    public void updateTemperatureUnit( final TemperatureUnit temperatureUnit ) {
        // Forward this method to the Natural Environment Pane.
        _naturalEnvironmentPane.updateTemperatureUnit( temperatureUnit );
    }
}