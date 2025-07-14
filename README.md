# FoodOrderX - Plataforma de Pedidos baseada em Microserviços com Spring Boot e Gateway API

💡 **Projeto:** Plataforma de Pedidos para Restaurantes (FoodOrderX)

---

## 🎯 Objetivo

Criar um sistema distribuído que simula a operação de pedidos em uma plataforma de restaurantes (estilo iFood simples), com diversos microserviços interligados através de um API Gateway.

---

## 🧱 Arquitetura de Microserviços

1. **API Gateway (Spring Cloud Gateway)**
    - Roteamento de requisições para os microserviços corretos.
    - Filtros de autenticação (JWT), logging, rate limiting, etc.

2. **Serviço de Autenticação (`auth-service`)**
    - CRUD de usuários e login.
    - Geração e validação de tokens JWT.
    - Registro/Login (cliente e restaurante).

3. **Serviço de Catálogo de Restaurantes (`restaurant-service`)**
    - Lista de restaurantes.
    - Cardápio de cada restaurante.
    - Consulta por tipo de comida, nome, cidade, etc.

4. **Serviço de Pedidos (`order-service`)**
    - Criação de pedidos com base no cardápio.
    - Atualização de status (recebido, em preparo, entregue).
    - Integração com o serviço de pagamento.

5. **Serviço de Pagamento (`payment-service`)**
    - Simulação de pagamento com cartões, PIX, etc.
    - Notificação de status do pagamento.
    - Integração com sistema de mensagens (RabbitMQ ou Kafka).

6. **Serviço de Entregas (`delivery-service`)** (opcional)
    - Atribuição de entregadores aos pedidos.
    - Rastreamento do status da entrega.

---

## 🧪 Tecnologias Utilizadas

| Componente     | Tecnologias                                                                 |
| -------------- | --------------------------------------------------------------------------- |
| **Backend**    | Spring Boot, Spring Cloud Gateway, Spring Security, JWT                     |
| **Comunicação**| REST, OpenFeign (para chamadas internas)                                    |
| **Mensageria** | RabbitMQ (para pedidos e pagamentos)                                        |
| **Banco**      | PostgreSQL (cada microserviço com seu DB)                                   |
| **Docker**     | Docker + Docker Compose para orquestração                                   |
| **Observabilidade** | Spring Boot Actuator, Zipkin ou Prometheus + Grafana                  |
| **Autenticação**| JWT com filtro no Gateway                                                  |
| **Documentação**| Swagger/OpenAPI em cada serviço                                            |

---

## 🧭 Fluxo de Exemplo (Pedido de Comida)

1. Cliente se autentica via `/auth/login` e recebe um token.
2. Cliente navega pelo catálogo em `/api/restaurants`.
3. Seleciona um restaurante e cria um pedido em `/api/orders`.
4. Pedido gera um evento para o `payment-service`.
5. Após o pagamento confirmado, o pedido é atualizado.
6. Entrega pode ser atribuída (opcional).

---

## 🚀 Funcionalidades para Explorar

- Filtragem e roteamento inteligente no Gateway (por path, header, IP).
- Filtros personalizados (ex: validar JWT no Gateway).
- Balanceamento de carga.
- Versionamento de APIs.
- Implementação de circuit breaker (com Resilience4J).
- Retry automático em caso de falhas.
- Rate limiting por usuário no Gateway.
