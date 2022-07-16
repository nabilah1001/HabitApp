package com.dicoding.habitapp.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.dicoding.habitapp.R
import com.dicoding.habitapp.utils.DarkMode

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            //TODO 11 : Update theme based on value in ListPreference
            themePreference()
        }

        private fun updateTheme(mode: Int): Boolean {
            AppCompatDelegate.setDefaultNightMode(mode)
            requireActivity().recreate()
            return true
        }

        private fun themePreference(){
            val preferenceTheme = findPreference<ListPreference>(getString(R.string.pref_key_dark))
            preferenceTheme?.setOnPreferenceChangeListener { _, newValue ->
                when(newValue){
                    "on"    -> updateTheme(DarkMode.ON.value)
                    "off"   -> updateTheme(DarkMode.OFF.value)
                    else  -> updateTheme(DarkMode.FOLLOW_SYSTEM.value)
                }
                true
            }
        }
    }
}