> ### Spots


> ### Casos de Sucesso

* [X] Recebi uma requisição do tipo <b> POST </b> na rota /spots.
* [X] Valida os campos obrigatórios <b> thumbnail</b>, <b> name</b>, <b> price</b>, <b> techs</b>.
* [X] Valida se o nome do spot já existe.
* [x] Cria um spot com os dados informados.
* [x] Retorna <b> 201</b> com os dados do spot criado.
* [ ] Retorna <b> 200 </b> com uma lista de usuários.  

> ### Casos de Exceções
* [x] Retorna 404 se a rota /spots não existir.
* [x] Retorna 400 quando os campos obrigatórios não forem fornecidos corretamente.
* [x] Retorna 400 se o nome do spot já existir.
* [x] Retorna 500 se o ocorrer um erro ao salvar um spot.
* [ ] Retorna 500 se o ocorrer um erro ao lista os usuários.