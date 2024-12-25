> ### Usuários.



> ### Casos de Sucesso

* [X] Recebi requisição do tipo  <b> POST </b> na rota /users.
* [X] Válida os campos obrigatórios <b> name </b> e <b> email </b>.
* [X] Válida se o campo e-mail é um e-mail válido.
* [X] Válida se já existe um usuário com um e-mail fornecido.
* [X] Cria um usuário com os dados informados.
* [X] Retorna <b> 201 </b> com os dados cadastrado do usuário.

> ### Exceções

* [X] Retorna 404 se a rota /users não existir.
* [X] Retorna 400 o campo nome e e-mail não forem informados.
* [X] Retorna 400 se o campo e-mail já existir.
* [X] Retorna 500 se ocorrer um erro ao cadastrar um usuário.