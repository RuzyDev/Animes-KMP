package br.com.arcom.apparcom.data.preferences

import platform.Foundation.NSUserDefaults

class IOSPreferencesManager : PreferencesManager {
    private val defaults = NSUserDefaults.standardUserDefaults

    override fun save(key: KeysPreferences, value: String) {
        defaults.setObject(value, key.name)
    }

    override fun get(key: KeysPreferences): String? {
        return defaults.stringForKey(key.name)
    }

    override fun remove(key: KeysPreferences) {
        defaults.removeObjectForKey(key.name)
    }
}