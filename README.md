# 👋 Welcome to BrainyExplorers!

Hey there! Thanks for checking out **BrainyExplorers** — an Android app built to make learning fun, safe, and interactive for kids. This project is all about giving young explorers a playful, secure space to discover educational labs, while making sure parents are in the loop with phone-based authentication. 

---

## 🌟 Why BrainyExplorers?

As a developer and lifelong learner, I wanted to create something that helps kids (and their parents!) explore cool science and learning content, all in one place. The app is designed to be:
- **Kid-friendly** (colorful, simple, and safe)
- **Parent-approved** (secure login with phone verification)
- **Easy to expand** (add more labs, new features, or your own twist!)

---

## 🚀 Features

- **Quick Login:** Parents sign in with their phone number and a magic OTP code (powered by Firebase).
- **Splash Screen:** A warm welcome and smart redirect — if you're already logged in, you go straight to the fun stuff!
- **Labs List:** Browse a collection of educational labs, fetched live from Firestore.
- **In-App WebView:** Tap a lab to explore its content right inside the app.
- **Modern UI:** Playful colors, custom fonts, and a friendly owl mascot.

---

## 🏗️ How It Works

- **SplashScreen** checks if you're logged in and sends you to the right place.
- **LoginActivity** handles phone number entry and OTP verification (with fragments for each step).
- **HomeActivity** shows the labs list (using RecyclerView, ViewModel, and LiveData).
- **WebViewActivity** opens up the lab's web content.
- **FirebaseUtils** wraps up all the Firebase Auth logic so you don't have to.

---

## 🗂️ Project Structure (in a nutshell)

```
app/
  └── src/
      └── main/
          └── java/com/shubham/brainyexplorers/
              ├── view/           # Activities & Fragments
              ├── ui/labs/        # Labs UI (Adapter, ViewModel)
              ├── ui/webview/     # WebView for labs
              ├── data/model/     # Data models
              ├── data/repository # Firestore access
              └── util/           # Firebase helpers
```

---

## 🛠️ Getting Started

1. **Clone this repo:**
   ```sh
   git clone <repo-url>
   cd BrainyExplorers
   ```
2. **Open in Android Studio** (recommended for best experience).
3. **Add your own `google-services.json`** to the `app/` folder (ask your Firebase project admin if you don't have one).
4. **Sync Gradle** and let it fetch all the goodies.
5. **Run the app** on your favorite emulator or Android device (minSdk 24).

---

## 🔒 Firebase & Permissions
- Uses **Firebase Authentication** (phone/OTP) and **Firestore** for labs data.
- Needs `INTERNET` permission (for login and loading labs).

---

## 🧩 Customizing & Contributing
- Want to add more labs? Just update the `labs` collection in Firestore.
- Change the look? Tweak the colors, images, or layouts in `res/`.
- Found a bug or have an idea? [Open an issue](#) or send a pull request — contributions are super welcome!

---

## 📦 Dependencies
- Kotlin, AndroidX, Material Components
- Firebase Auth, Firestore
- ViewModel, LiveData, Coroutines
- Navigation, ConstraintLayout, Compose (for future features)

---

## 📄 License
*Add your license here (MIT, Apache, etc.)*

---

> Made with ❤️ for curious minds. 
>
> **BrainyExplorers** — Explore • Learn • Grow 