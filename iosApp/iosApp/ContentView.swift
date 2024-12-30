import SwiftUI
import shared

struct ContentView: View {
	var body: some View {
        LoginView(path: $path)
        AppNavigation()
	}
}
