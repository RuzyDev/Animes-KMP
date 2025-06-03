import UIKit
import FirebaseCore       // Para inicializar o Firebase
import FirebaseMessaging   // Para mensagens FCM
import shared              // Seu módulo Kotlin Multiplatform
import UserNotifications   // Para lidar com notificações do sistema

@main // Adicionado para apps com iOS 14+ usando o novo ciclo de vida
class AppDelegate: UIResponder, UIApplicationDelegate, UNUserNotificationCenterDelegate, MessagingDelegate {

    var window: UIWindow? // Necessário se `@main` estiver na SceneDelegate
    // ... (outras propriedades, se houver)

    // MARK: - `application(_:didFinishLaunchingWithOptions:)`

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // 1. Inicializar o Firebase
        FirebaseApp.configure()

        // 2. Configurar o delegate do UNUserNotificationCenter
        UNUserNotificationCenter.current().delegate = self

        // 3. Solicitar permissões de notificação (assincronamente)
        Task { @MainActor in // Use Task para async/await na thread principal
            do {
                let authOptions: UNAuthorizationOptions = [.alert, .badge, .sound]
                let granted = try await UNUserNotificationCenter.current().requestAuthorization(options: authOptions)
                print("Permissão para notificações concedida: \(granted)")
                if !granted {
                    print("Erro ao solicitar permissão de notificação.")
                }
            } catch {
                print("Erro ao solicitar permissão de notificação: \(error.localizedDescription)")
            }

            // 4. Registrar para notificações remotas (APNs)
            // Deve ser chamado na thread principal.
            application.registerForRemoteNotifications()
        }


        // 5. Configurar o delegate do Firebase Messaging
        Messaging.messaging().delegate = self

        return true
    }

    // MARK: - UNUserNotificationCenterDelegate (APIs Modernas)

    // Chamado quando uma notificação é recebida enquanto o app está em PRIMEIRO PLANO
    func userNotificationCenter(_ center: UNUserNotificationCenter,
                                willPresent notification: UNNotification,
                                withCompletionHandler completionHandler: @escaping (UNNotificationPresentationOptions) -> Void) {
        let userInfo = notification.request.content.userInfo
        print("Notificação recebida em primeiro plano: \(userInfo)")

        // Converte o userInfo (NSDictionary) para [String: String]? para o Kotlin
        let notificationData = (userInfo as? [String: Any])?.compactMapValues { $0 as? String } ?? [:]
        NotificationManager().onMessageReceived(data: notificationData) // Chama o Kotlin

        completionHandler([.banner, .sound, .badge]) // Exibe a notificação
    }

    // Chamado quando o usuário interage (clica) com uma notificação
    func userNotificationCenter(_ center: UNUserNotificationCenter,
                                didReceive response: UNNotificationResponse,
                                withCompletionHandler completionHandler: @escaping () -> Void) {
        let userInfo = response.notification.request.content.userInfo
        print("Notificação clicada: \(userInfo)")

        // Converte o userInfo (NSDictionary) para [String: String]? para o Kotlin
        let notificationData = (userInfo as? [String: Any])?.compactMapValues { $0 as? String } ?? [:]
        NotificationManager().onMessageReceived(data: notificationData) // Chama o Kotlin (pode usar handleNotificationClick se preferir)

        completionHandler()
    }

    // MARK: - Firebase Messaging Delegate

    // Chamado quando um novo token FCM é gerado ou atualizado
    func messaging(_ messaging: Messaging, didReceiveRegistrationToken fcmToken: String?) {
        print("Token de registro Firebase (FCM): \(fcmToken ?? "NULO")")
        // Você deve enviar este token para o seu backend.
        // Opcional: Se precisar do token no Kotlin, você pode criar uma função em NotificationManager para isso.
        // Ex: NotificationManager().onNewToken(fcmToken)
    }

    // Chamado quando uma mensagem remota do FCM é recebida (especialmente mensagens de dados)
    // Isso é para processar notificações em segundo plano ou quando o app está ativo
    func application(_ application: UIApplication, didReceiveRemoteNotification userInfo: [AnyHashable: Any],
                             fetchCompletionHandler completionHandler: @escaping (UIBackgroundFetchResult) -> Void) {
        print("Notificação remota recebida (didReceiveRemoteNotification): \(userInfo)")

        // Deixe o Firebase processar a mensagem (útil para Analytics, etc.)
        Messaging.messaging().appDidReceiveMessage(userInfo)

        // Converte o userInfo (NSDictionary) para [String: String]? para o Kotlin
        let notificationData = (userInfo as? [String: Any])?.compactMapValues { $0 as? String } ?? [:]
        NotificationManager().onMessageReceived(data: notificationData) // Chama o Kotlin

        completionHandler(.newData) // Indica que novos dados foram processados
    }

    // MARK: - Registro de Token APNs

    // Chamado após o registro bem-sucedido para notificações remotas (APNs)
    func application(_ application: UIApplication, didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data) {
        // Importante: Firebase usa o token APNs para gerar seu próprio token FCM.
        Messaging.messaging().apnsToken = deviceToken
        print("Device Token APNs registrado com sucesso.")
    }

    // Chamado se o registro para notificações remotas falhar
    func application(_ application: UIApplication, didFailToRegisterForRemoteNotificationsWithError error: Error) {
        print("Falha ao registrar para notificações remotas: \(error.localizedDescription)")
    }
}
