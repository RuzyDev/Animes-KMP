
import SwiftUI

struct SemDados: View {
    
    let label: String
    
    var body: some View {
        VStack{
            
            Image("ic_sem_dados")
                .resizable()
                .scaledToFit()
                .frame(height: 200)
            
            Text(label)
                .font(.title3)
                .fontWeight(.bold)
                .frame(maxWidth: .infinity, alignment: .center)
                .padding(.top, 8)
                .padding(.horizontal, UIScreen.main.bounds.width * 0.05)
            
        }
    }
}
