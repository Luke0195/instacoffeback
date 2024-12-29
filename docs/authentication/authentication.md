> ### Autenticação


> ### Casos de Sucesso

* [X] Recebi uma requisição do tipo <b> POST </b> na rota /authenticate.
* [X] Valida os campo obrigatório <b>email</b>.
* [X] Valida se o email já existe.
* [x] Retorna o usuário informado.
* [x] Retorna <b> 200</b> com os dados do usuário criado.
* 
> ### Casos de Exceções
* [x] Retorna 404 se a rota /authentication não existir.
* [x] Retorna 400 quando os campos obrigatórios não forem fornecidos corretamente.
* [x] Retorna 400 se o email do usuário não existir.
* [x] Retorna 500 se ocorrer um erro ao buscar ao email.
