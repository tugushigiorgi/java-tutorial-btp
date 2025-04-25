# 🚀 Spring Boot Hello World – SAP BTP Auth with App Router & XSUAA

This is a simple Spring Boot "Hello World" app deployed on SAP BTP. It uses App Router and XSUAA for authentication and role-based authorization.

---

## 🧩 Components


### 🔹 XSUAA (Authorization)
- Defined in `xs-security.json`
- Defines role template: `Viewer`

### 🔹 App Router
- Handles login via XSUAA
- Forwards `/helloworld/**` to the backend
- Passes JWT token to backend

### 🔹 Role Collection
- Created manually in SAP BTP Cockpit
- Assigns `Viewer` role from your app to a user
- Required for access via App Router

---

## 🔐 Access Flow

- ❌ **Direct access** to backend: `401 Unauthorized`
- ❌ **App Router access** without role: `403 Forbidden`
- ✅ **App Router with Viewer role**: Access granted

---

## URL
-https://helloworld-fantastic-hyrax-zv.cfapps.us10-001.hana.ondemand.com/
-Router  https://web-tired-buffalo-kh.cfapps.us10-001.hana.ondemand.com/
