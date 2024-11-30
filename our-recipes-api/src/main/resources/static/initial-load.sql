INSERT INTO public.tb_users (email,"password",username) VALUES
	 ('testador@test.ok','$2a$10$cUAcnS.JooOKw7dZUXXBT.wpCScClZ9cifkKvbV2GiWB75SVtEHN2','testador'),
	 ('teste@teste.com','$2a$10$FKhrHkDBMA0IRgc5Kk.b9eTghgpS4afHnbe5NoDJz6n3JwNe5DTcK','testador2'),
	 ('teste3@teste.com','$2a$10$NXFyoF1034OLOxhHhVQbzetv60/vMztsDxjyMRtjPzgY54R4Olioa','testador3');

INSERT INTO public.tb_roles (user_id,role_id) VALUES
	 (1,'ROLE_USER'),
	 (2,'ROLE_USER'),
	 (3,'ROLE_USER');

INSERT INTO tb_recipe (active,description,difficulty,"name",preparation_time,rating,serving_size,author_id) VALUES
	 (true,'Basico arroz branco para acompanhamento de molhos, carnes e saladas e até mesmo aquele feijão gostoso.','BEGINNER','Arroz Branco',15,4.0,4,4),
	 (true,'Uma deliciosa sopa de legumes para os dias mais frios, cheia de nutrientes e sabor.','BEGINNER','Sopa de Legumes',30,5.0,6,2),
	 (true,'Molho de tomate caseiro, perfeito para massas ou pizzas.','BEGINNER','Molho de Tomate',20,5.0,8,2),
	 (true,'Pão de queijo tradicional, macio por dentro e crocante por fora.','BEGINNER','Pão de Queijo',40,5.0,6,3),
	 (true,'Sanduíche natural de frango com cenoura ralada, ótimo para lanches rápidos.','BEGINNER','Sanduíche Natural',10,5.0,1,2),
	 (true,'Espaguete ao alho e óleo, um clássico fácil e rápido.','BEGINNER','Espaguete Alho e Óleo',20,5.0,4,4),
	 (true,'Purê de batatas cremoso, perfeito para acompanhar carnes e assados.','BEGINNER','Purê de Batatas',25,5.0,4,2),
	 (true,'Smoothie de frutas vermelhas, ideal para refrescar dias quentes.','BEGINNER','Smoothie de Frutas Vermelhas',10,5.0,1,3),
	 (true,'Panqueca americana com mel ou geleia, ótima para o café da manhã.','BEGINNER','Panqueca Americana',20,5.0,2,4),
	 (true,'Salada de frutas tropical, um toque de frescor e saúde.','BEGINNER','Salada de Frutas',10,5.0,4,2);

INSERT INTO tb_recipe (active,description,difficulty,"name",preparation_time,rating,serving_size,author_id) VALUES
	 (true,'Torradas com manteiga e alho, um aperitivo simples e saboroso.','BEGINNER','Torradas de Alho',15,5.0,3,3),
	 (true,'Mingau de aveia com leite e canela, uma sobremesa simples e aconchegante.','BEGINNER','Mingau de Aveia',15,4.0,2,3),
	 (true,'Omelete simples e rápida, perfeita para café da manhã ou um jantar leve.','BEGINNER','Omelete Simples',10,4.0,2,3),
	 (true,'Vitamina de banana e aveia, ideal para um café da manhã energético.','BEGINNER','Vitamina de Banana',5,3.0,1,4),
	 (false,'Salada verde com toque de limão e azeite, ideal para um acompanhamento saudável.','BEGINNER','Salada Verde',5,5.0,4,4)

INSERT INTO public.tb_recipe_ingredient (quantity,ingredient_id,measurement_unit_id,recipe_id) VALUES
	 (3.0,1,10,1),
	 (2.0,98,6,1),
	 (4.0,38,6,1),
	 (0.5,11,5,1),
	 (3.0,10,12,1),
	 (2.0,95,11,3),
	 (3.0,1,10,3),
	 (2.0,97,11,3),
	 (3.0,13,10,3),
	 (2.0,9,5,3),
	 (2.0,176,8,3);

INSERT INTO public.tb_recipe_tag (recipe_id,tag_id) VALUES
	 (9,'sobremesa'),
	 (9,'doce'),
	 (9,'leve'),
	 (9,'comfort_food'),
	 (3,'low_carb'),
	 (3,'salgado'),
	 (3,'rápido'),
	 (3,'sem_gluten'),
	 (3,'café_da_manhã'),
	 (6,'assado');
INSERT INTO public.tb_recipe_tag (recipe_id,tag_id) VALUES
	 (6,'salgado'),
	 (6,'sem_gluten'),
	 (6,'café_da_manhã'),
	 (1,'acompanhamento'),
	 (1,'salgado'),
	 (1,'vegano'),
	 (1,'sem_gluten'),
	 (12,'doce'),
	 (12,'sobremesa'),
	 (12,'saudável');
INSERT INTO public.tb_recipe_tag (recipe_id,tag_id) VALUES
	 (12,'rápido'),
	 (15,'lanche'),
	 (15,'grelhado'),
	 (15,'salgado'),
	 (15,'café_da_manhã'),
	 (15,'rápido');


INSERT INTO public.tb_step (description,step_number,recipe_id) VALUES
	 ('Em um recipiente a aparte coloque a agua pra aquecer.',1,1),
	 ('Pique a cebola e amasse o alho.',2,1),
	 ('Na panela de preparo refogue a cebola',3,1),
	 ('Quando a cebola estiver dourando acrescente o alho e refogue rapidamente',4,1),
	 ('Acrescente o arroz e de uma refogada rapida também',5,1),
	 ('Quando o arroz estiver frito acrescente a água fervendo.',6,1),
	 ('Adicione o sal, e mexa, corrija o sal se for necessário.',7,1),
	 ('Observe, quando a agua baixar ao nível do arroz, baixe o fogo',8,1),
	 ('Confira com uma colher se existe agua no fundo',9,1),
	 ('Quando secar pode servir.',10,1);

