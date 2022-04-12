package com.fruitmonster.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		//Sets the android version of the app to fullscreen
		config.useImmersiveMode = true;
		//Smoother Rendering
		config.numSamples = 2;

		config.useAccelerometer = true;
		config.useCompass = true;
		config.useWakelock = true;

		initialize(new FruitMonster(), config);
	}
}
