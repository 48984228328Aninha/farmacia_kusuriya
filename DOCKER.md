# 🐳 Docker - Guia de Execução

Este guia explica como executar o projeto Farmácia Kusuriya usando Docker e Docker Compose.

---

## 📋 Pré-requisitos

- Docker instalado (versão 20.10 ou superior)
- Docker Compose instalado (versão 2.0 ou superior)

### Verificar instalação

```bash
docker --version
docker-compose --version
```

---

## 🚀 Iniciando o Projeto

### 1. Subir todos os serviços

```bash
docker-compose up -d
```

Este comando irá:
- Baixar as imagens necessárias (PostgreSQL e Eclipse Temurin)
- Construir a imagem da aplicação
- Criar a network isolada
- Iniciar o banco de dados PostgreSQL
- Aguardar o banco estar saudável (healthcheck)
- Iniciar a aplicação Spring Boot

### 2. Verificar status dos containers

```bash
docker-compose ps
```

Saída esperada:
```
NAME                      STATUS              PORTS
farmacia-kusuriya-app     Up                  0.0.0.0:8080->8080/tcp
farmacia-kusuriya-db      Up (healthy)        0.0.0.0:5432->5432/tcp
```

### 3. Acessar a aplicação

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs
- **Health Check**: http://localhost:8080/actuator/health (se configurado)

---

## 📊 Logs e Monitoramento

### Ver logs de todos os serviços

```bash
docker-compose logs -f
```

### Ver logs apenas da aplicação

```bash
docker-compose logs -f app
```

### Ver logs apenas do banco de dados

```bash
docker-compose logs -f postgres
```

### Ver últimas 100 linhas de log

```bash
docker-compose logs --tail=100 app
```

---

## 🗄️ Acessando o Banco de Dados

### Via psql (dentro do container)

```bash
docker exec -it farmacia-kusuriya-db psql -U postgres -d farmacia_kusuriya
```

### Comandos úteis no psql

```sql
-- Listar tabelas
\dt

-- Descrever estrutura de uma tabela
\d paciente

-- Ver dados
SELECT * FROM paciente;
SELECT * FROM farmacia;
SELECT * FROM medico;

-- Sair
\q
```

### Via cliente externo

- **Host**: localhost
- **Port**: 5432
- **Database**: farmacia_kusuriya
- **User**: postgres
- **Password**: postgres

---

## 🔄 Gerenciamento dos Containers

### Parar os serviços

```bash
docker-compose stop
```

### Iniciar serviços parados

```bash
docker-compose start
```

### Reiniciar os serviços

```bash
docker-compose restart
```

### Parar e remover containers

```bash
docker-compose down
```

### Parar e remover containers + volumes (⚠️ apaga dados)

```bash
docker-compose down -v
```

---

## 🔨 Rebuild e Atualização

### Rebuild da aplicação após mudanças no código

```bash
docker-compose up -d --build app
```

### Rebuild completo (app + banco)

```bash
docker-compose up -d --build
```

### Forçar recriação dos containers

```bash
docker-compose up -d --force-recreate
```

---

## 🧪 Executando Testes

### Testes unitários (sem Docker)

```bash
mvn test -Dtest=!KusuriyaApplicationTests
```

### Testes de integração (com Docker)

```bash
# Subir apenas o banco
docker-compose up -d postgres

# Aguardar banco estar pronto
sleep 5

# Executar testes
mvn test

# Parar banco
docker-compose down
```

---

## 🐛 Troubleshooting

### Problema: Porta 8080 já em uso

```bash
# Verificar o que está usando a porta
netstat -ano | findstr :8080

# Parar o processo ou mudar a porta no docker-compose.yml
ports:
  - "8081:8080"  # Usar porta 8081 no host
```

### Problema: Porta 5432 já em uso

```bash
# Verificar PostgreSQL local
# Parar PostgreSQL local ou mudar porta no docker-compose.yml
ports:
  - "5433:5432"  # Usar porta 5433 no host
```

### Problema: Aplicação não conecta no banco

```bash
# Verificar logs
docker-compose logs app

# Verificar se banco está healthy
docker-compose ps

# Reiniciar serviços
docker-compose restart
```

### Problema: Erro de permissão no volume

```bash
# Remover volumes e recriar
docker-compose down -v
docker-compose up -d
```

### Limpar tudo e começar do zero

```bash
# Parar e remover tudo
docker-compose down -v

# Remover imagens
docker rmi farmacia_kusuriya-app

# Limpar cache do Docker
docker system prune -a

# Subir novamente
docker-compose up -d --build
```

---

## 📦 Estrutura dos Volumes

### Volume do PostgreSQL

```bash
# Listar volumes
docker volume ls

# Inspecionar volume
docker volume inspect farmacia_kusuriya_postgres_data

# Backup do volume
docker run --rm -v farmacia_kusuriya_postgres_data:/data -v $(pwd):/backup alpine tar czf /backup/postgres-backup.tar.gz /data
```

---

## 🔐 Variáveis de Ambiente

### Aplicação (app service)

| Variável | Valor | Descrição |
|----------|-------|-----------|
| SPRING_DATASOURCE_URL | jdbc:postgresql://postgres:5432/farmacia_kusuriya | URL do banco |
| SPRING_DATASOURCE_USERNAME | postgres | Usuário do banco |
| SPRING_DATASOURCE_PASSWORD | postgres | Senha do banco |
| SPRING_JPA_HIBERNATE_DDL_AUTO | validate | Modo do Hibernate |
| SPRING_FLYWAY_ENABLED | true | Habilitar Flyway |

### PostgreSQL (postgres service)

| Variável | Valor | Descrição |
|----------|-------|-----------|
| POSTGRES_DB | farmacia_kusuriya | Nome do banco |
| POSTGRES_USER | postgres | Usuário |
| POSTGRES_PASSWORD | postgres | Senha |

---

## 🌐 Network

### Inspecionar a network

```bash
docker network inspect farmacia_kusuriya_farmacia-network
```

### Testar conectividade entre containers

```bash
# Entrar no container da app
docker exec -it farmacia-kusuriya-app sh

# Testar conexão com o banco
ping postgres
nc -zv postgres 5432
```

---

## 📈 Monitoramento de Recursos

### Ver uso de recursos

```bash
docker stats
```

### Ver uso de disco

```bash
docker system df
```

---

## 🎯 Comandos Rápidos

```bash
# Subir tudo
docker-compose up -d

# Ver logs
docker-compose logs -f

# Parar tudo
docker-compose down

# Rebuild app
docker-compose up -d --build app

# Acessar banco
docker exec -it farmacia-kusuriya-db psql -U postgres -d farmacia_kusuriya

# Ver status
docker-compose ps

# Reiniciar app
docker-compose restart app
```

---

## 📚 Referências

- [Docker Documentation](https://docs.docker.com/)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [PostgreSQL Docker Image](https://hub.docker.com/_/postgres)
- [Eclipse Temurin Docker Image](https://hub.docker.com/_/eclipse-temurin)

---

## ✅ Checklist de Verificação

Após subir o projeto, verifique:

- [ ] Containers estão rodando: `docker-compose ps`
- [ ] Banco está healthy: Status deve mostrar "(healthy)"
- [ ] Aplicação está respondendo: http://localhost:8080/swagger-ui.html
- [ ] Logs não mostram erros: `docker-compose logs app`
- [ ] Banco de dados acessível: `docker exec -it farmacia-kusuriya-db psql -U postgres -d farmacia_kusuriya`
- [ ] Migrations aplicadas: Verificar tabelas no banco

---

**Projeto pronto para desenvolvimento! 🚀**
