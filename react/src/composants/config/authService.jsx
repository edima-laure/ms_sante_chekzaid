import axiosInstance from "./axiosConfig";
import { API_BASE } from "./apiconfig";

class AuthService {
  constructor() {
    this.initializeTokenExpiredListener();
  }

  /* ===================== TOKEN EXPIRED ===================== */

  initializeTokenExpiredListener() {
    if (!window.authServiceTokenExpiredListenerAdded) {
      window.addEventListener("tokenExpired", (event) => {
        this.handleTokenExpired(
          event.detail?.message,
          event.detail?.reason
        );
      });
      window.authServiceTokenExpiredListenerAdded = true;
    }
  }

  handleTokenExpired(message, reason = "TOKEN_EXPIRED") {
    this.clearLocalSession();
    this.showNotification(
      "Votre session a expiré. Veuillez vous reconnecter.",
      "warning",
      5000
    );
    this.redirectToLogin();
  }

  /* ===================== LOGIN ===================== */

  async login(email, password) {
    const response = await axiosInstance.post(`${API_BASE}/login`, {
      email,
      password,
    });

    const { id, token, username, photoUrl, authorities } = response.data;

    localStorage.setItem("token", token);
    localStorage.setItem("id", id);
    localStorage.setItem("username", username);
    localStorage.setItem("photoUrl", photoUrl);
    localStorage.setItem(
      "user",
      JSON.stringify(authorities?.[0]?.authority)
    );

    return response.data;
  }

  /* ===================== LOGOUT ===================== */

  async logout() {
    try {
      const token = localStorage.getItem("token");

      if (token) {
        await axiosInstance.post(
          `${API_BASE}/logout`,
          {},
          { headers: { Authorization: `Bearer ${token}` } }
        );
      }
    } catch (error) {
      console.warn("Erreur logout backend ignorée");
    } finally {
      this.clearLocalSession();
    }

    return { success: true };
  }

  async logoutAndRedirect() {
    await this.logout();
    this.showNotification("Déconnexion réussie", "success", 3000);
    this.redirectToLogin();
  }

  /* ===================== SESSION ===================== */

  clearLocalSession() {
    const rememberedEmail = localStorage.getItem("rememberedEmail");

    localStorage.removeItem("token");
    localStorage.removeItem("id");
    localStorage.removeItem("username");
    localStorage.removeItem("photoUrl");
    localStorage.removeItem("user");

    if (rememberedEmail) {
      localStorage.setItem("rememberedEmail", rememberedEmail);
    }
  }

  isAuthenticated() {
    return !!localStorage.getItem("token");
  }

  isUserFullyAuthenticated() {
    const token = localStorage.getItem("token");
    const id = localStorage.getItem("id");
    const username = localStorage.getItem("username");
    const role = localStorage.getItem("user");

    return !!(token && id && username && role && this.isTokenValid(token));
  }

  /* ===================== JWT ===================== */

  isTokenValid(token) {
    const payload = this.decodeJWT(token);
    if (!payload?.exp) return false;

    if (payload.exp < Date.now() / 1000) {
      this.handleTokenExpired();
      return false;
    }
    return true;
  }

  decodeJWT(token) {
    try {
      const base64 = token.split(".")[1];
      return JSON.parse(atob(base64));
    } catch {
      return null;
    }
  }

  /* ===================== NOTIFICATIONS ===================== */

  showNotification(message, type = "info", duration = 3000) {
    if (window.showNotification) {
      window.showNotification(message, type, duration);
    } else {
      console.log(`[${type}] ${message}`);
    }
  }

  /* ===================== NAVIGATION ===================== */

  redirectToLogin() {
    if (window.location.pathname !== "/") {
      window.location.href = "/";
    }
  }
}

/* ===================== EXPORT ===================== */

const authService = new AuthService();
export default authService;
