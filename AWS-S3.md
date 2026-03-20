# ☁️ AWS S3 - Configuração e Uso

Este guia explica como configurar o AWS S3 para armazenamento de receitas médicas.

---

## 📋 Pré-requisitos

- Conta AWS ativa
- AWS CLI instalado (opcional)
- Credenciais AWS (Access Key ID e Secret Access Key)

---

## 🔐 Configuração de Credenciais

### 1. Criar usuário IAM na AWS

1. Acesse o console AWS: https://console.aws.amazon.com/
2. Navegue para **IAM** > **Users** > **Create user**
3. Nome do usuário: `farmacia-kusuriya-s3-user`
4. Marque: **Programmatic access**
5. Clique em **Next: Permissions**

### 2. Configurar Permissões

Anexe a política: **AmazonS3FullAccess** (ou crie uma política customizada)

#### Política Customizada (Recomendado)
```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "s3:PutObject",
        "s3:GetObject",
        "s3:DeleteObject",
        "s3:ListBucket"
      ],
      "Resource": [
        "arn:aws:s3:::farmacia-kusuriya-receitas",
        "arn:aws:s3:::farmacia-kusuriya-receitas/*"
      ]
    }
  ]
}
```

### 3. Obter Credenciais

Após criar o usuário, você receberá:
- **Access Key ID**: `AKIAXXXXXXXXXXXXXXXX`
- **Secret Access Key**: `xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx`

⚠️ **IMPORTANTE**: Guarde essas credenciais em local seguro!

---

## 🪣 Criar Bucket S3

### Via Console AWS

1. Acesse **S3** no console AWS
2. Clique em **Create bucket**
3. Nome do bucket: `farmacia-kusuriya-receitas`
4. Região: `us-east-1` (ou sua preferência)
5. Desmarque: **Block all public access** (se necessário acesso público)
6. Clique em **Create bucket**

### Via AWS CLI

```bash
aws s3 mb s3://farmacia-kusuriya-receitas --region us-east-1
```

---

## ⚙️ Configuração Local

### 1. Criar arquivo .env

Copie o arquivo `.env.example` para `.env`:

```bash
cp .env.example .env
```

### 2. Preencher credenciais no .env

```env
AWS_ACCESS_KEY_ID=AKIAXXXXXXXXXXXXXXXX
AWS_SECRET_ACCESS_KEY=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
AWS_S3_BUCKET_NAME=farmacia-kusuriya-receitas
AWS_S3_REGION=us-east-1
```

⚠️ **NUNCA** commite o arquivo `.env` no Git!

---

## 🐳 Configuração com Docker

### 1. Carregar variáveis de ambiente

O `docker-compose.yml` já está configurado para ler as variáveis do `.env`:

```yaml
environment:
  AWS_ACCESS_KEY_ID: ${AWS_ACCESS_KEY_ID}
  AWS_SECRET_ACCESS_KEY: ${AWS_SECRET_ACCESS_KEY}
  AWS_S3_BUCKET_NAME: ${AWS_S3_BUCKET_NAME:-farmacia-kusuriya-receitas}
  AWS_S3_REGION: ${AWS_S3_REGION:-us-east-1}
```

### 2. Subir aplicação

```bash
docker-compose up -d
```

---

## 🧪 Testar Configuração

### Via Swagger

1. Acesse: http://localhost:8080/swagger-ui.html
2. Endpoint: `POST /receitas/upload`
3. Faça upload de um arquivo PDF ou imagem
4. Verifique se o arquivo foi enviado para o S3

### Via cURL

```bash
curl -X POST http://localhost:8080/receitas/upload \
  -F "arquivo=@receita.pdf" \
  -F "pacienteId=1"
```

### Verificar no S3

```bash
aws s3 ls s3://farmacia-kusuriya-receitas/
```

---

## 📊 Estrutura de Armazenamento

### Formato de Nome de Arquivo

```
UUID-receita.extensao
Exemplo: 550e8400-e29b-41d4-a716-446655440000.pdf
```

### URL de Acesso

```
https://farmacia-kusuriya-receitas.s3.us-east-1.amazonaws.com/550e8400-e29b-41d4-a716-446655440000.pdf
```

---

## 🔒 Segurança

### Boas Práticas

1. ✅ Use IAM roles em produção (não credenciais hardcoded)
2. ✅ Configure bucket policy para acesso restrito
3. ✅ Habilite versionamento no bucket
4. ✅ Configure lifecycle policies para arquivos antigos
5. ✅ Use HTTPS sempre
6. ✅ Configure CORS se necessário

### Bucket Policy (Exemplo)

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "AllowAppAccess",
      "Effect": "Allow",
      "Principal": {
        "AWS": "arn:aws:iam::ACCOUNT_ID:user/farmacia-kusuriya-s3-user"
      },
      "Action": [
        "s3:GetObject",
        "s3:PutObject",
        "s3:DeleteObject"
      ],
      "Resource": "arn:aws:s3:::farmacia-kusuriya-receitas/*"
    }
  ]
}
```

---

## 💰 Custos

### Estimativa AWS S3

- **Armazenamento**: $0.023 por GB/mês (us-east-1)
- **Requisições PUT**: $0.005 por 1.000 requisições
- **Requisições GET**: $0.0004 por 1.000 requisições
- **Transferência de dados**: Primeiros 100 GB/mês grátis

### Exemplo de Uso

- 1.000 receitas/mês
- Tamanho médio: 500 KB
- Total: ~500 MB

**Custo mensal estimado**: < $1.00 USD

---

## 🛠️ Troubleshooting

### Erro: Access Denied

```
Verificar:
- Credenciais corretas no .env
- Permissões do usuário IAM
- Nome do bucket correto
- Região correta
```

### Erro: Bucket does not exist

```bash
# Criar bucket
aws s3 mb s3://farmacia-kusuriya-receitas --region us-east-1
```

### Erro: Invalid credentials

```
Verificar:
- AWS_ACCESS_KEY_ID está correto
- AWS_SECRET_ACCESS_KEY está correto
- Credenciais não expiraram
```

### Testar credenciais

```bash
aws s3 ls --profile default
```

---

## 🔄 Alternativa: LocalStack (Desenvolvimento)

Para desenvolvimento local sem AWS real:

### docker-compose.yml

```yaml
localstack:
  image: localstack/localstack
  container_name: localstack
  ports:
    - "4566:4566"
  environment:
    - SERVICES=s3
    - DEFAULT_REGION=us-east-1
    - DATA_DIR=/tmp/localstack/data
  volumes:
    - localstack_data:/tmp/localstack
```

### Configurar endpoint local

```properties
# application-dev.properties
aws.s3.endpoint=http://localhost:4566
```

---

## 📚 Referências

- [AWS S3 Documentation](https://docs.aws.amazon.com/s3/)
- [AWS SDK for Java](https://docs.aws.amazon.com/sdk-for-java/)
- [IAM Best Practices](https://docs.aws.amazon.com/IAM/latest/UserGuide/best-practices.html)
- [S3 Pricing](https://aws.amazon.com/s3/pricing/)

---

## ✅ Checklist de Configuração

- [ ] Conta AWS criada
- [ ] Usuário IAM criado
- [ ] Credenciais obtidas
- [ ] Bucket S3 criado
- [ ] Arquivo .env configurado
- [ ] Variáveis no docker-compose.yml
- [ ] Testes passando
- [ ] Upload funcionando
- [ ] Arquivo visível no S3

---

**Configuração completa! ☁️**
