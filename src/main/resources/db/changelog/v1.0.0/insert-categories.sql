-- Корневые категории (parent_id = NULL)
INSERT INTO categories (name, slug, description, sort_order, is_active) VALUES
    ('Бытовая техника', 'bytovaya-tekhnika', 'Техника для дома и кухни', 10, true),
    ('Крупная бытовая техника', 'krupnaya-bytovaya-tekhnika', 'Холодильники, стиральные машины и другое', 20, true),
    ('Встраиваемая техника', 'vstraivaemaya-tekhnika', 'Техника для кухонных гарнитуров', 30, true),
    ('Климатическая техника', 'klimaticheskaya-tekhnika', 'Кондиционеры, обогреватели, вентиляторы', 40, true),
    ('Техника для кухни', 'tekhnika-dlya-kukhni', 'Мелкая кухонная техника', 50, true),
    ('Техника для дома', 'tekhnika-dlya-doma', 'Пылесосы, утюги, пароочистители', 60, true),
    ('Аудио и видео техника', 'audio-i-video-tekhnika', 'Телевизоры, аудиосистемы', 70, true),
    ('Компьютерная техника', 'kompyuternaya-tekhnika', 'Ноутбуки, компьютеры, периферия', 80, true),
    ('Фото и видео', 'foto-i-video', 'Камеры, объективы, аксессуары', 90, true),
    ('Телефоны и гаджеты', 'telefony-i-gadzhety', 'Смартфоны, планшеты, умные часы', 100, true);

-- Подкатегории для "Бытовая техника" (id = 1)
WITH cat AS (SELECT id FROM categories WHERE slug = 'bytovaya-tekhnika')
INSERT INTO categories (name, slug, description, parent_id, sort_order) VALUES
    ('Холодильники', 'kholodilniki', 'Холодильники и морозильные камеры', (SELECT id FROM cat), 110),
    ('Стиральные машины', 'stiralnye-mashiny', 'Стиральные и сушильные машины', (SELECT id FROM cat), 120),
    ('Посудомоечные машины', 'posudomoechnye-mashiny', 'Посудомоечные машины', (SELECT id FROM cat), 130),
    ('Микроволновые печи', 'mikrovolnovye-pechi', 'Микроволновки и СВЧ', (SELECT id FROM cat), 140),
    ('Плиты и духовки', 'plity-i-dukhovki', 'Газовые и электрические плиты, духовые шкафы', (SELECT id FROM cat), 150),
    ('Вытяжки', 'vytyazhki', 'Кухонные вытяжки', (SELECT id FROM cat), 160),
    ('Мелкая кухонная техника', 'melkaya-kukhonnaya-tekhnika', 'Чайники, тостеры, блендеры', (SELECT id FROM cat), 170);

-- Подкатегории для "Крупная бытовая техника" (id = 2)
WITH cat AS (SELECT id FROM categories WHERE slug = 'krupnaya-bytovaya-tekhnika')
INSERT INTO categories (name, slug, description, parent_id, sort_order) VALUES
    ('Холодильники', 'krupnye-kholodilniki', 'Двухкамерные и Side-by-Side холодильники', (SELECT id FROM cat), 210),
    ('Морозильные камеры', 'morozilnye-kamery', 'Отдельно стоящие морозильники', (SELECT id FROM cat), 220),
    ('Стиральные машины', 'krupnye-stiralnye-mashiny', 'Стиральные машины автомат', (SELECT id FROM cat), 230),
    ('Сушильные машины', 'sushilnye-mashiny', 'Автоматические сушилки для белья', (SELECT id FROM cat), 240),
    ('Посудомоечные машины', 'krupnye-posudomoechnye-mashiny', 'Полноразмерные посудомойки', (SELECT id FROM cat), 250),
    ('Электрические плиты', 'elektricheskie-plity', 'Электрические плиты', (SELECT id FROM cat), 260),
    ('Газовые плиты', 'gazovye-plity', 'Газовые плиты', (SELECT id FROM cat), 270);

-- Подкатегории для "Встраиваемая техника" (id = 3)
WITH cat AS (SELECT id FROM categories WHERE slug = 'vstraivaemaya-tekhnika')
INSERT INTO categories (name, slug, description, parent_id, sort_order) VALUES
    ('Встраиваемые холодильники', 'vstraivaemye-kholodilniki', 'Холодильники под столешницу', (SELECT id FROM cat), 310),
    ('Встраиваемые посудомойки', 'vstraivaemye-posudomoyki', 'Посудомоечные машины для встраивания', (SELECT id FROM cat), 320),
    ('Встраиваемые духовки', 'vstraivaemye-dukhovki', 'Духовые шкафы', (SELECT id FROM cat), 330),
    ('Встраиваемые варочные панели', 'varochnye-paneli', 'Газовые и индукционные панели', (SELECT id FROM cat), 340),
    ('Встраиваемые микроволновки', 'vstraivaemye-mikrovolnovki', 'СВЧ для встраивания', (SELECT id FROM cat), 350),
    ('Встраиваемые кофемашины', 'vstraivaemye-kofemashiny', 'Кофемашины встраиваемые', (SELECT id FROM cat), 360);

-- Подкатегории для "Климатическая техника" (id = 4)
WITH cat AS (SELECT id FROM categories WHERE slug = 'klimaticheskaya-tekhnika')
INSERT INTO categories (name, slug, description, parent_id, sort_order) VALUES
    ('Кондиционеры', 'konditsionery', 'Сплит-системы и кондиционеры', (SELECT id FROM cat), 410),
    ('Обогреватели', 'obogrevateli', 'Масляные, конвекторные, инфракрасные', (SELECT id FROM cat), 420),
    ('Вентиляторы', 'ventilyatory', 'Напольные, настольные, колонные', (SELECT id FROM cat), 430),
    ('Увлажнители воздуха', 'uvlazhniteli-vozdukha', 'Увлажнители и очистители', (SELECT id FROM cat), 440),
    ('Осушители воздуха', 'osushiteli-vozdukha', 'Осушители для дома', (SELECT id FROM cat), 450),
    ('Тепловентиляторы', 'teploventilyatory', 'Тепловые пушки и вентиляторы', (SELECT id FROM cat), 460);

-- Подкатегории для "Техника для кухни" (id = 5)
WITH cat AS (SELECT id FROM categories WHERE slug = 'tekhnika-dlya-kukhni')
INSERT INTO categories (name, slug, description, parent_id, sort_order) VALUES
    ('Чайники', 'chainiki', 'Электрические чайники', (SELECT id FROM cat), 510),
    ('Кофеварки и кофемашины', 'kofevarki', 'Кофеварки капельные, рожковые', (SELECT id FROM cat), 520),
    ('Блендеры', 'blendery', 'Стационарные и погружные', (SELECT id FROM cat), 530),
    ('Миксеры', 'miksery', 'Ручные и планетарные миксеры', (SELECT id FROM cat), 540),
    ('Мясорубки', 'myasorubki', 'Электрические мясорубки', (SELECT id FROM cat), 550),
    ('Тостеры', 'tostery', 'Тостеры и сэндвичницы', (SELECT id FROM cat), 560),
    ('Мультиварки', 'multivarki', 'Мультиварки и скороварки', (SELECT id FROM cat), 570),
    ('Хлебопечки', 'khlebopechki', 'Хлебопекарные машины', (SELECT id FROM cat), 580),
    ('Соковыжималки', 'sokovyzhimalki', 'Соковыжималки для овощей и фруктов', (SELECT id FROM cat), 590),
    ('Кухонные комбайны', 'kukhonnye-kombainy', 'Многофункциональные комбайны', (SELECT id FROM cat), 600);

-- Подкатегории для "Техника для дома" (id = 6)
WITH cat AS (SELECT id FROM categories WHERE slug = 'tekhnika-dlya-doma')
INSERT INTO categories (name, slug, description, parent_id, sort_order) VALUES
    ('Пылесосы', 'pylesosy', 'Обычные и моющие пылесосы', (SELECT id FROM cat), 610),
    ('Роботы-пылесосы', 'roboty-pylesosy', 'Автоматические пылесосы', (SELECT id FROM cat), 620),
    ('Вертикальные пылесосы', 'vertikalnye-pylesosy', 'Беспроводные вертикальные пылесосы', (SELECT id FROM cat), 630),
    ('Пароочистители', 'paroochistiteli', 'Отпариватели и пароочистители', (SELECT id FROM cat), 640),
    ('Утюги', 'utyugi', 'Паровые утюги и гладильные системы', (SELECT id FROM cat), 650),
    ('Швейные машины', 'shveinye-mashiny', 'Швейные и вышивальные машины', (SELECT id FROM cat), 660);

-- Подкатегории для "Аудио и видео техника" (id = 7)
WITH cat AS (SELECT id FROM categories WHERE slug = 'audio-i-video-tekhnika')
INSERT INTO categories (name, slug, description, parent_id, sort_order) VALUES
    ('Телевизоры', 'televizory', 'LED, OLED, QLED телевизоры', (SELECT id FROM cat), 710),
    ('Мониторы', 'monitory', 'Компьютерные мониторы', (SELECT id FROM cat), 720),
    ('Акустические системы', 'akustika', 'Колонки и саундбары', (SELECT id FROM cat), 730),
    ('Наушники', 'naushniki', 'Проводные и беспроводные наушники', (SELECT id FROM cat), 740),
    ('Плееры', 'pleery', 'MP3 плееры и медиаплееры', (SELECT id FROM cat), 750),
    ('Домашние кинотеатры', 'domashnie-kinotiatry', 'Системы домашнего кинотеатра', (SELECT id FROM cat), 760);

-- Подкатегории для "Компьютерная техника" (id = 8)
WITH cat AS (SELECT id FROM categories WHERE slug = 'kompyuternaya-tekhnika')
INSERT INTO categories (name, slug, description, parent_id, sort_order) VALUES
    ('Ноутбуки', 'noutbuki', 'Портативные компьютеры', (SELECT id FROM cat), 810),
    ('Компьютеры', 'kompyutery', 'Системные блоки и моноблоки', (SELECT id FROM cat), 820),
    ('Планшеты', 'planshety', 'Планшетные компьютеры', (SELECT id FROM cat), 830),
    ('Клавиатуры и мыши', 'klaviatury-i-myshi', 'Устройства ввода', (SELECT id FROM cat), 840),
    ('Мониторы', 'kompyuternye-monitory', 'Мониторы для ПК', (SELECT id FROM cat), 850),
    ('Принтеры и сканеры', 'printery-i-skannery', 'Печатающие устройства', (SELECT id FROM cat), 860),
    ('Комплектующие', 'komplektuyushchie', 'Процессоры, видеокарты, память', (SELECT id FROM cat), 870),
    ('Внешние накопители', 'vneshnie-nakopiteli', 'Жесткие диски, флешки', (SELECT id FROM cat), 880);

-- Подкатегории для "Фото и видео" (id = 9)
WITH cat AS (SELECT id FROM categories WHERE slug = 'foto-i-video')
INSERT INTO categories (name, slug, description, parent_id, sort_order) VALUES
    ('Фотоаппараты', 'fotoapparaty', 'Зеркальные и беззеркальные камеры', (SELECT id FROM cat), 910),
    ('Компактные камеры', 'kompaktnye-kamery', 'Мыльницы и ультразумы', (SELECT id FROM cat), 920),
    ('Объективы', 'obektivy', 'Сменная оптика', (SELECT id FROM cat), 930),
    ('Видеокамеры', 'videokamery', 'Любительские и профессиональные', (SELECT id FROM cat), 940),
    ('Экшн-камеры', 'ekshn-kamery', 'Камеры для активного отдыха', (SELECT id FROM cat), 950),
    ('Штативы', 'shtativy', 'Треноги и моноподы', (SELECT id FROM cat), 960),
    ('Вспышки', 'vspyshki', 'Фотовспышки', (SELECT id FROM cat), 970);

-- Подкатегории для "Телефоны и гаджеты" (id = 10)
WITH cat AS (SELECT id FROM categories WHERE slug = 'telefony-i-gadzhety')
INSERT INTO categories (name, slug, description, parent_id, sort_order) VALUES
    ('Смартфоны', 'smartfony', 'Мобильные телефоны', (SELECT id FROM cat), 1010),
    ('Аксессуары для телефонов', 'aksessuary-dlya-telefonov', 'Чехлы, защитные стекла', (SELECT id FROM cat), 1020),
    ('Умные часы', 'umnye-chasy', 'Смарт-часы и фитнес-браслеты', (SELECT id FROM cat), 1030),
    ('Power Bank', 'power-bank', 'Внешние аккумуляторы', (SELECT id FROM cat), 1040),
    ('Зарядные устройства', 'zaryadnye-ustroystva', 'Блоки питания и кабели', (SELECT id FROM cat), 1050),
    ('Держатели', 'derzhateli', 'Автомобильные держатели', (SELECT id FROM cat), 1060);