# 📞 Call Center Sales Management System

A Spring Boot web application for call center team leaders to manage sales agents and track their performance with statistics.

---

## 🛠️ Technology Stack

| Layer | Technology |
|---|---|
| Backend | Spring Boot 3.x |
| Database | H2 (dev) / MySQL / PostgreSQL (prod) |
| ORM | Spring Data JPA |
| Template Engine | Thymeleaf |
| Frontend | HTML5, CSS3, Bootstrap 5, JavaScript |
| Security | Spring Security |
| Build Tool | Maven |

---

## ✨ Features

### Agent
- Login / Logout
- View own online/offline status
- Upload a sale (Contract ID + PDF file)
- View own sales history

### Manager
- Login / Logout
- View all agents and their online/offline status
- View all sales
- Filter sales by agent or team
- Edit / Delete sales

---

## 🗄️ Database Schema

### TEAMS
| Field | Description |
|---|---|
| `id` | Primary key |
| `name` | Team name (unique) |
| `note` | Team note |

### USERS
| Field | Description |
|---|---|
| `id` | Primary key |
| `username` | Username |
| `password` | Hashed password |
| `email` | Email address |
| `role` | `Agent` or `Manager` |
| `team_name` | FK → Teams |
| `status` | `Active` or `Inactive` |

### SALES
| Field | Description |
|---|---|
| `id` | Primary key |
| `agent_id` | FK → Users |
| `contract_id` | Contract identifier |
| `contract_file` | PDF file path |
| `sale_status` | `Completed` or `Cancelled` |
| `created_at` | Timestamp |

### Relationships

```
Team (1) ──────── (*) User (1) ──────── (*) Sales
```

---

## 🔌 API Endpoints

### Authentication
| Method | Endpoint | Description |
|---|---|---|
| POST | `/login` | User login |
| POST | `/logout` | User logout |

### Teams
| Method | Endpoint | Description |
|---|---|---|
| GET | `/teams` | List all teams |
| GET | `/teams/{id}` | View team details |
| GET | `/teams/new` | Create team form |
| POST | `/teams` | Save new team |
| GET | `/teams/{id}/edit` | Edit team form |
| PUT | `/teams/{id}` | Update team |
| DELETE | `/teams/{id}` | Delete team |

### Agents
| Method | Endpoint | Description |
|---|---|---|
| GET | `/agents` | List all agents |
| GET | `/agents/{id}` | View agent details |
| GET | `/agents/new` | Create agent form |
| POST | `/agents` | Save new agent |
| GET | `/agents/{id}/edit` | Edit agent form |
| PUT | `/agents/{id}` | Update agent |
| DELETE | `/agents/{id}` | Delete agent |
| GET | `/agents/team/{teamId}` | List agents by team |

### Sales
| Method | Endpoint | Description |
|---|---|---|
| GET | `/sales` | List all sales |
| GET | `/sales/{id}` | View sale details |
| GET | `/sales/new` | Create sale form |
| POST | `/sales` | Save new sale |
| GET | `/sales/{id}/edit` | Edit sale form |
| PUT | `/sales/{id}` | Update sale |
| DELETE | `/sales/{id}` | Delete sale |
| GET | `/sales/agent/{agentId}` | List sales by agent |
| GET | `/sales/filter` | Filter sales by criteria |

### Dashboard & Statistics
| Method | Endpoint | Description |
|---|---|---|
| GET | `/dashboard` | Main dashboard |
| GET | `/statistics/agent/{id}` | Agent statistics |
| GET | `/statistics/team/{id}` | Team statistics |
| GET | `/statistics/report` | Generate reports |

---

## 🧭 Controller Overview

### `AuthController`
Handles `/login` and `/logout`.

### `ManagerController`
Handles manager-scoped routes:
- `GET /manager/dashboard`
- `GET /manager/sales` — view, filter, edit, delete sales
- `GET /manager/agents` — view, create, edit, delete agents
- `POST /manager/teams` — manage teams

### `AgentController`
Handles agent-scoped routes:
- `GET /agent/dashboard`
- `GET /agent/add-sale` — sale submission form
- `POST /agent/save-sale` — submit a sale
- `GET /agent/my-sales` — view personal sales

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- MySQL or PostgreSQL (for production)

