# 用户登录注册接口文档

## 1. 用户注册

### 接口信息
- **接口地址**：`/api/user/register`
- **请求方式**：POST
- **接口描述**：用户注册功能，注册成功后返回用户ID

### 请求参数
| 参数名   | 类型   | 必填 | 描述     | 示例值         |
|---------|--------|-----|----------|---------------|
| email   | String | 是  | 邮箱     | user@example.com |
| password| String | 是  | 密码     | password123   |
| name    | String | 是  | 姓名     | 张三           |
| school  | String | 是  | 学校     | 某大学         |
| major   | String | 否  | 专业     | 计算机科学     |

### 请求示例
```json
{
  "email": "user@example.com",
  "password": "password123",
  "name": "张三",
  "school": "某大学",
  "major": "计算机科学"
}
```

### 响应参数
| 参数名   | 类型   | 描述         | 示例值         |
|---------|--------|--------------|---------------|
| code    | Integer| 状态码，200表示成功 | 200       |
| message | String | 提示信息      | success       |
| data    | String | 用户ID        | 550e8400-e29b-41d4-a716-446655440000 |

### 响应示例
```json
{
  "code": 200,
  "message": "success",
  "data": "550e8400-e29b-41d4-a716-446655440000"
}
```

### 错误码
| 错误码  | 描述         | 原因说明         |
|--------|--------------|-----------------|
| 400    | 参数错误      | 邮箱已被注册     |
| 500    | 服务器错误    | 服务器内部错误   |

## 2. 用户登录

### 接口信息
- **接口地址**：`/api/user/login`
- **请求方式**：POST
- **接口描述**：用户登录功能，登录成功后返回用户信息和token

### 请求参数
| 参数名   | 类型   | 必填 | 描述     | 示例值         |
|---------|--------|-----|----------|---------------|
| email   | String | 是  | 邮箱     | user@example.com |
| password| String | 是  | 密码     | password123   |

### 请求示例
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

### 响应参数
| 参数名   | 类型   | 描述         | 示例值         |
|---------|--------|--------------|---------------|
| code    | Integer| 状态码，200表示成功 | 200       |
| message | String | 提示信息      | success       |
| data    | Object | 用户数据      |               |
| data.userId | String | 用户ID     | 550e8400-e29b-41d4-a716-446655440000 |
| data.token | String | 访问令牌    | eyJhbGciOiJIUzI1NiJ9... |
| data.name  | String | 用户姓名    | 张三          |
| data.email | String | 用户邮箱    | user@example.com |
| data.school| String | 用户学校    | 某大学        |
| data.major | String | 用户专业    | 计算机科学    |

### 响应示例
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "userId": "550e8400-e29b-41d4-a716-446655440000",
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "name": "张三",
    "email": "user@example.com",
    "school": "某大学",
    "major": "计算机科学"
  }
}
```

### 错误码
| 错误码  | 描述         | 原因说明         |
|--------|--------------|-----------------|
| 401    | 认证失败      | 用户名或密码错误 |
| 500    | 服务器错误    | 服务器内部错误   |

## 3. 检查邮箱是否已注册

### 接口信息
- **接口地址**：`/api/user/checkEmail`
- **请求方式**：GET
- **接口描述**：检查邮箱是否已被注册，返回布尔值

### 请求参数
| 参数名   | 类型   | 必填 | 描述     | 示例值         |
|---------|--------|-----|----------|---------------|
| email   | String | 是  | 邮箱     | user@example.com |

### 请求示例
```
GET /api/user/checkEmail?email=user@example.com
```

### 响应参数
| 参数名   | 类型   | 描述         | 示例值         |
|---------|--------|--------------|---------------|
| code    | Integer| 状态码，200表示成功 | 200       |
| message | String | 提示信息      | success       |
| data    | Boolean| 是否已注册    | true/false    |

### 响应示例
```json
{
  "code": 200,
  "message": "success",
  "data": true
}
```

### 错误码
| 错误码  | 描述         | 原因说明         |
|--------|--------------|-----------------|
| 500    | 服务器错误    | 服务器内部错误   | 