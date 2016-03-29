Features
===========

 - List Countries.
 - List States.
 - List Schools.
 - List School-districts.



###List Countries

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | GET |
| End Point | /api/nucleus/{version}/lookups/countries |
| Auth | Required |
| Request Body Type | None |
| Response Code | 200 Successful |


**Request Parameters**

| Name | Comments |
|------------------------|--------|
| keyword | optional |


**Authorization Header**

```
Authorization: Token MTQ1NzM1ODIwMDI1MTo0Y2M4MWQ4ZS03MjFjLTQ5ZDItYmVmMy00M2EzNjhiNTg4NzA6YmE5NTZhOTctYWUxNS0xMWU1LWEzMDItZjhhOTYzMDY1OTc2
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token MTQ1NzM1ODIwMDI1MTo0Y2M4MWQ4ZS03MjFjLTQ5ZDItYmVmMy00M2EzNjhiNTg4NzA6YmE5NTZhOTctYWUxNS0xMWU1LWEzMDItZjhhOTYzMDY1OTc2" -X GET http://nucleus.gooru.org/api/nucleus/v1/lookups/countries
```

###List States

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | GET |
| End Point | /api/nucleus/{version}/lookups/countries/{country-id}/states |
| Auth | Required |
| Request Body Type | None |
| Response Code | 200 Successful |


**Request Parameters**

| Name | Comments |
|------------------------|--------|
| keyword | optional |


**Authorization Header**

```
Authorization: Token MTQ1NzM1ODIwMDI1MTo0Y2M4MWQ4ZS03MjFjLTQ5ZDItYmVmMy00M2EzNjhiNTg4NzA6YmE5NTZhOTctYWUxNS0xMWU1LWEzMDItZjhhOTYzMDY1OTc2
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token MTQ1NzM1ODIwMDI1MTo0Y2M4MWQ4ZS03MjFjLTQ5ZDItYmVmMy00M2EzNjhiNTg4NzA6YmE5NTZhOTctYWUxNS0xMWU1LWEzMDItZjhhOTYzMDY1OTc2" -X GET http://nucleus.gooru.org/api/nucleus/v1/lookups/countries/{country-id}/states
```

###List School Districts

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | GET |
| End Point | /api/nucleus/{version}/lookups/school-districts |
| Auth | Required |
| Request Body Type | None |
| Response Code | 200 Successful |


**Request Parameters**

| Name | Comments |
|------------------------|--------|
| keyword | optional |
| state_id | optional |
| limit | optional, default value 20 |
| offset | optional, default value 0 |

**Authorization Header**

```
Authorization: Token MTQ1NzM1ODIwMDI1MTo0Y2M4MWQ4ZS03MjFjLTQ5ZDItYmVmMy00M2EzNjhiNTg4NzA6YmE5NTZhOTctYWUxNS0xMWU1LWEzMDItZjhhOTYzMDY1OTc2
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token MTQ1NzM1ODIwMDI1MTo0Y2M4MWQ4ZS03MjFjLTQ5ZDItYmVmMy00M2EzNjhiNTg4NzA6YmE5NTZhOTctYWUxNS0xMWU1LWEzMDItZjhhOTYzMDY1OTc2" -X GET http://nucleus.gooru.org/api/nucleus/v1/lookups/school-districts
```


###List Schools

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | GET |
| End Point | /api/nucleus/{version}/lookups/schools |
| Auth | Required |
| Request Body Type | None |
| Response Code | 200 Successful |


**Request Parameters**

| Name | Comments |
|------------------------|--------|
| keyword | optional |
| school_district_id | optional |
| limit | optional, default value 20 |
| offset | optional, default value 0 |

**Authorization Header**

```
Authorization: Token MTQ1NzM1ODIwMDI1MTo0Y2M4MWQ4ZS03MjFjLTQ5ZDItYmVmMy00M2EzNjhiNTg4NzA6YmE5NTZhOTctYWUxNS0xMWU1LWEzMDItZjhhOTYzMDY1OTc2
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token MTQ1NzM1ODIwMDI1MTo0Y2M4MWQ4ZS03MjFjLTQ5ZDItYmVmMy00M2EzNjhiNTg4NzA6YmE5NTZhOTctYWUxNS0xMWU1LWEzMDItZjhhOTYzMDY1OTc2" -X GET http://nucleus.gooru.org/api/nucleus/v1/lookups/schools
```
