UPDATE categories
SET is_active = false,
    description = CONCAT(description, ' (категория отключена, используйте "Бытовая техника")')
WHERE slug IN (
       'krupnaya-bytovaya-tekhnika',
       'vstraivaemaya-tekhnika'
    );