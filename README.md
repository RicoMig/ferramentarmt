# RMT Tool - Java Web Application

## English
This project is an RMT (Real Money Trading) management tool for game-related calculations. It is built using a modern Java stack and follows clean architecture principles.

### Technologies
- **Java 24**: Utilizing the latest features of the Java platform.
- **Maven**: For dependency management and build automation.
- **MongoDB**: Used as the primary data store for flexible and scalable persistence.
- **Jakarta Servlet**: For handled web requests.

### Features
- **End-to-End Calculator**: Complete flow from frontend to MongoDB.
- **Dynamic Market Price**: Override system rates with live daily market values.
- **Multi-Currency Support**: Choose between BRL, USD, and EUR as target currencies (BRL as base).
- **MongoDB DAO Pattern**: Implementation with generic `MongoDAO` and specific `CalculatorDAO`.
- **Automated Seeding**: Integrated script to synchronize database parameters with UI options.
- **Singleton Connection**: Efficient MongoDB connection management.

---

## Português
Este projeto é uma ferramenta de gerenciamento de RMT (Real Money Trading) para cálculos relacionados a jogos. Foi desenvolvido utilizando uma stack Java moderna e segue princípios de arquitetura limpa.

### Tecnologias
- **Java 24**: Utilizando os recursos mais recentes da plataforma Java.
- **Maven**: Para gerenciamento de dependências e automação de build.
- **MongoDB**: Utilizado como banco de dados principal para persistência flexível e escalável.
- **Jakarta Servlet**: Para manipulação de requisições web.

### Funcionalidades
- **Calculadora Ponta-a-Ponta**: Fluxo completo do frontend ao MongoDB.
- **Preço de Mercado Dinâmico**: Sobrescrita de taxas do sistema com valores reais do mercado diário.
- **Suporte Multi-Moeda**: Escolha entre BRL, USD e EUR como moedas de destino (BRL como base).
- **Padrão DAO com MongoDB**: Implementação com `MongoDAO` genérico e `CalculatorDAO` específico.
- **Seed Automatizado**: Script integrado para sincronizar parâmetros do banco com opções da UI.
- **Gerenciamento Singleton**: Gerenciamento eficiente de conexão com MongoDB.
