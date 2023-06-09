package com.outofmilk.outofmilk.controllers;

import com.google.gson.*;
import com.outofmilk.outofmilk.models.Ingredient;
import com.outofmilk.outofmilk.models.Recipe;
import com.outofmilk.outofmilk.models.User;
import com.outofmilk.outofmilk.repositories.IngredientRepository;
import com.outofmilk.outofmilk.repositories.RecipeRepository;
import com.outofmilk.outofmilk.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.image.AreaAveragingScaleFilter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class RestController {

    @Value("${mealdb.api.key}")
    private String apiKey;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private IngredientRepository ingredientRepository;

    private final List<Ingredient> ingredients = new ArrayList<>();

    private long recipeLikes = 0;

    private long recipeLiked = 0;

    private long recipeHidden = 0;

    @GetMapping("/recipe/{id}")
    public String callExternalApi(@PathVariable int id, Model model) {
        String jsonResponse = null;

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getReferenceById(loggedInUser.getId());

        if (user == null) {
            return "/login";
        }

        model.addAttribute("user", user);

        try {
            System.out.println(apiKey);
            URL url = new URL("https://www.themealdb.com/api/json/v2/1/lookup.php?i=" + id);
//            URL url = new URL("https://www.themealdb.com/api/json/v2/" + apiKey + "/lookup.php?i=" + id);
//            URL url = new URL("https://www.themealdb.com/api/json/v2/" + apiKey + "/lookup.php?i=52814");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestMethod("GET");
            connection.getResponseCode();
            jsonResponse = new String(connection.getInputStream().readAllBytes());
            System.out.println("HTTP response code is " + connection.getResponseCode());
            System.out.println(jsonResponse);

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
            System.out.println(jsonObject);
            JsonArray mealsArray = JsonParser.parseString(jsonResponse).getAsJsonObject().getAsJsonArray("meals");
            System.out.println(mealsArray);
            for (JsonElement mealElement : mealsArray) {
                JsonObject mealObject = mealElement.getAsJsonObject();
                String idMeal = mealObject.get("idMeal").getAsString();
                String strMeal = mealObject.get("strMeal").getAsString();
                String strCategory = mealObject.get("strCategory").getAsString();
                String strInstructions = mealObject.get("strInstructions").getAsString();
                String strMealThumb = mealObject.get("strMealThumb").getAsString();

                String strYoutube = "";
                if (!mealObject.get("strYoutube").isJsonNull()) {
                    strYoutube = mealObject.get("strYoutube").getAsString();
                }

                String strIngredient1 = "";
                Ingredient ingredient1 = null;
                if (!mealObject.get("strIngredient1").isJsonNull()) {
                    strIngredient1 = mealObject.get("strIngredient1").getAsString();
                    if (!strIngredient1.isEmpty()) {
                        ingredient1 = ingredientRepository.findByName(strIngredient1.toLowerCase()).get(0);
                        ingredients.add(ingredient1);
                    }
                }

                String strIngredient2 = "";
                Ingredient ingredient2 = null;
                if (!mealObject.get("strIngredient2").isJsonNull()) {
                    strIngredient2 = mealObject.get("strIngredient2").getAsString();
                    if (!strIngredient2.isEmpty()) {
                        ingredient2 = ingredientRepository.findByName(strIngredient2.toLowerCase()).get(0);
                        ingredients.add(ingredient2);
                    }
                }

                String strIngredient3 = "";
                Ingredient ingredient3 = null;
                if (!mealObject.get("strIngredient3").isJsonNull()) {
                    strIngredient3 = mealObject.get("strIngredient3").getAsString();
                    if (!strIngredient3.isEmpty()) {
                        ingredient3 = ingredientRepository.findByName(strIngredient3.toLowerCase()).get(0);
                        ingredients.add(ingredient3);
                    }
                }

                String strIngredient4 = "";
                Ingredient ingredient4 = null;
                if (!mealObject.get("strIngredient4").isJsonNull()) {
                    strIngredient4 = mealObject.get("strIngredient4").getAsString();
                    if (!strIngredient4.isEmpty()) {
                        ingredient4 = ingredientRepository.findByName(strIngredient4.toLowerCase()).get(0);
                        ingredients.add(ingredient4);
                    }
                }

                String strIngredient5 = "";
                Ingredient ingredient5 = null;
                if (!mealObject.get("strIngredient5").isJsonNull()) {
                    strIngredient5 = mealObject.get("strIngredient5").getAsString();
                    if (!strIngredient5.isEmpty()) {
                        ingredient5 = ingredientRepository.findByName(strIngredient5.toLowerCase()).get(0);
                        ingredients.add(ingredient5);
                    }
                }

                String strIngredient6 = "";
                Ingredient ingredient6 = null;
                if (!mealObject.get("strIngredient6").isJsonNull()) {
                    strIngredient6 = mealObject.get("strIngredient6").getAsString();
                    if (!strIngredient6.isEmpty()) {
                        ingredient6 = ingredientRepository.findByName(strIngredient6.toLowerCase()).get(0);
                        ingredients.add(ingredient6);
                    }
                }
                String strIngredient7 = "";
                Ingredient ingredient7 = null;
                if (!mealObject.get("strIngredient7").isJsonNull()) {
                    strIngredient7 = mealObject.get("strIngredient7").getAsString();
                    if (!strIngredient7.isEmpty()) {
                        ingredient7 = ingredientRepository.findByName(strIngredient7.toLowerCase()).get(0);
                        ingredients.add(ingredient7);
                    }
                }
                String strIngredient8 = "";
                Ingredient ingredient8 = null;
                if (!mealObject.get("strIngredient8").isJsonNull()) {
                    strIngredient8 = mealObject.get("strIngredient8").getAsString();
                    if (!strIngredient8.isEmpty()) {
                        ingredient8 = ingredientRepository.findByName(strIngredient8.toLowerCase()).get(0);
                        ingredients.add(ingredient8);
                    }
                }
                String strIngredient9 = "";
                Ingredient ingredient9 = null;
                if (!mealObject.get("strIngredient9").isJsonNull()) {
                    strIngredient9 = mealObject.get("strIngredient9").getAsString();
                    if (!strIngredient9.isEmpty()) {
                        ingredient9 = ingredientRepository.findByName(strIngredient9.toLowerCase()).get(0);
                        ingredients.add(ingredient9);
                    }
                }
                String strIngredient10 = "";
                Ingredient ingredient10 = null;
                if (!mealObject.get("strIngredient10").isJsonNull()) {
                    strIngredient10 = mealObject.get("strIngredient10").getAsString();
                    if (!strIngredient10.isEmpty()) {
                        ingredient10 = ingredientRepository.findByName(strIngredient10.toLowerCase()).get(0);
                        ingredients.add(ingredient10);
                    }
                }
                String strIngredient11 = "";
                Ingredient ingredient11 = null;
                if (!mealObject.get("strIngredient11").isJsonNull()) {
                    strIngredient11 = mealObject.get("strIngredient11").getAsString();
                    if (!strIngredient11.isEmpty()) {
                        ingredient11 = ingredientRepository.findByName(strIngredient11.toLowerCase()).get(0);
                        ingredients.add(ingredient11);
                    }
                }
                String strIngredient12 = "";
                Ingredient ingredient12 = null;
                if (!mealObject.get("strIngredient12").isJsonNull()) {
                    strIngredient12 = mealObject.get("strIngredient12").getAsString();
                    if (!strIngredient12.isEmpty()) {
                        ingredient12 = ingredientRepository.findByName(strIngredient12.toLowerCase()).get(0);
                        ingredients.add(ingredient12);
                    }
                }
                String strIngredient13 = "";
                Ingredient ingredient13 = null;
                if (!mealObject.get("strIngredient13").isJsonNull()) {
                    strIngredient13 = mealObject.get("strIngredient13").getAsString();
                    if (!strIngredient13.isEmpty()) {
                        ingredient13 = ingredientRepository.findByName(strIngredient13.toLowerCase()).get(0);
                        ingredients.add(ingredient13);
                    }
                }
                String strIngredient14 = "";
                Ingredient ingredient14 = null;
                if (!mealObject.get("strIngredient14").isJsonNull()) {
                    strIngredient14 = mealObject.get("strIngredient14").getAsString();
                    if (!strIngredient14.isEmpty()) {
                        ingredient14 = ingredientRepository.findByName(strIngredient14.toLowerCase()).get(0);
                        ingredients.add(ingredient14);
                    }
                }
                String strIngredient15 = "";
                Ingredient ingredient15 = null;
                if (!mealObject.get("strIngredient15").isJsonNull()) {
                    strIngredient15 = mealObject.get("strIngredient15").getAsString();
                    if (!strIngredient15.isEmpty()) {
                        ingredient15 = ingredientRepository.findByName(strIngredient15.toLowerCase()).get(0);
                        ingredients.add(ingredient15);
                    }
                }
                String strIngredient16 = "";
                Ingredient ingredient16 = null;
                if (!mealObject.get("strIngredient16").isJsonNull()) {
                    strIngredient16 = mealObject.get("strIngredient16").getAsString();
                    if (!strIngredient16.isEmpty()) {
                        ingredient16 = ingredientRepository.findByName(strIngredient16.toLowerCase()).get(0);
                        ingredients.add(ingredient16);
                    }
                }
                String strIngredient17 = "";
                Ingredient ingredient17 = null;
                if (!mealObject.get("strIngredient17").isJsonNull()) {
                    strIngredient17 = mealObject.get("strIngredient17").getAsString();
                    if (!strIngredient17.isEmpty()) {
                        ingredient17 = ingredientRepository.findByName(strIngredient17.toLowerCase()).get(0);
                        ingredients.add(ingredient17);
                    }
                }
                String strIngredient18 = "";
                Ingredient ingredient18 = null;
                if (!mealObject.get("strIngredient18").isJsonNull()) {
                    strIngredient18 = mealObject.get("strIngredient18").getAsString();
                    if (!strIngredient18.isEmpty()) {
                        ingredient18 = ingredientRepository.findByName(strIngredient18.toLowerCase()).get(0);
                        ingredients.add(ingredient18);
                    }
                }
                String strIngredient19 = "";
                Ingredient ingredient19 = null;
                if (!mealObject.get("strIngredient19").isJsonNull()) {
                    strIngredient19 = mealObject.get("strIngredient19").getAsString();
                    if (!strIngredient19.isEmpty()) {
                        ingredient19 = ingredientRepository.findByName(strIngredient19.toLowerCase()).get(0);
                        ingredients.add(ingredient19);
                    }
                }
                String strIngredient20 = "";
                Ingredient ingredient20 = null;
                if (!mealObject.get("strIngredient20").isJsonNull()) {
                    strIngredient20 = mealObject.get("strIngredient20").getAsString();
                    if (!strIngredient20.isEmpty()) {
                        ingredient20 = ingredientRepository.findByName(strIngredient20.toLowerCase()).get(0);
                        ingredients.add(ingredient20);
                    }
                }

                ingredients.removeAll(Arrays.asList("", null));

                String strMeasure1 = "";
                if (!mealObject.get("strMeasure1").isJsonNull()) {
                    strMeasure1 = mealObject.get("strMeasure1").getAsString();
                }
                String strMeasure2 = "";
                if (!mealObject.get("strMeasure2").isJsonNull()) {
                    strMeasure2 = mealObject.get("strMeasure2").getAsString();
                }
                String strMeasure3 = "";
                if (!mealObject.get("strMeasure3").isJsonNull()) {
                    strMeasure3 = mealObject.get("strMeasure3").getAsString();
                }
                String strMeasure4 = "";
                if (!mealObject.get("strMeasure4").isJsonNull()) {
                    strMeasure4 = mealObject.get("strMeasure4").getAsString();
                }
                String strMeasure5 = "";
                if (!mealObject.get("strMeasure5").isJsonNull()) {
                    strMeasure5 = mealObject.get("strMeasure5").getAsString();
                }
                String strMeasure6 = "";
                if (!mealObject.get("strMeasure6").isJsonNull()) {
                    strMeasure6 = mealObject.get("strMeasure6").getAsString();
                }
                String strMeasure7 = "";
                if (!mealObject.get("strMeasure7").isJsonNull()) {
                    strMeasure7 = mealObject.get("strMeasure7").getAsString();
                }
                String strMeasure8 = "";
                if (!mealObject.get("strMeasure8").isJsonNull()) {
                    strMeasure8 = mealObject.get("strMeasure8").getAsString();
                }
                String strMeasure9 = "";
                if (!mealObject.get("strMeasure9").isJsonNull()) {
                    strMeasure9 = mealObject.get("strMeasure9").getAsString();
                }
                String strMeasure10 = "";
                if (!mealObject.get("strMeasure10").isJsonNull()) {
                    strMeasure10 = mealObject.get("strMeasure10").getAsString();
                }
                String strMeasure11 = "";
                if (!mealObject.get("strMeasure11").isJsonNull()) {
                    strMeasure11 = mealObject.get("strMeasure11").getAsString();
                }
                String strMeasure12 = "";
                if (!mealObject.get("strMeasure12").isJsonNull()) {
                    strMeasure12 = mealObject.get("strMeasure12").getAsString();
                }
                String strMeasure13 = "";
                if (!mealObject.get("strMeasure13").isJsonNull()) {
                    strMeasure13 = mealObject.get("strMeasure13").getAsString();
                }
                String strMeasure14 = "";
                if (!mealObject.get("strMeasure14").isJsonNull()) {
                    strMeasure14 = mealObject.get("strMeasure14").getAsString();
                }
                String strMeasure15 = "";
                if (!mealObject.get("strMeasure15").isJsonNull()) {
                    strMeasure15 = mealObject.get("strMeasure15").getAsString();
                }
                String strMeasure16 = "";
                if (!mealObject.get("strMeasure16").isJsonNull()) {
                    strMeasure16 = mealObject.get("strMeasure16").getAsString();
                }
                String strMeasure17 = "";
                if (!mealObject.get("strMeasure17").isJsonNull()) {
                    strMeasure17 = mealObject.get("strMeasure17").getAsString();
                }
                String strMeasure18 = "";
                if (!mealObject.get("strMeasure18").isJsonNull()) {
                    strMeasure18 = mealObject.get("strMeasure18").getAsString();
                }
                String strMeasure19 = "";
                if (!mealObject.get("strMeasure19").isJsonNull()) {
                    strMeasure19 = mealObject.get("strMeasure19").getAsString();
                }
                String strMeasure20 = "";
                if (!mealObject.get("strMeasure20").isJsonNull()) {
                    strMeasure20 = mealObject.get("strMeasure20").getAsString();
                }


                model.addAttribute("idmeal", idMeal);
                model.addAttribute("strMeal", strMeal);
                model.addAttribute("strCategory", strCategory);
                model.addAttribute("strInstructions", strInstructions);
                model.addAttribute("strYoutube", strYoutube);
                model.addAttribute("strMealThumb", strMealThumb);
                model.addAttribute("strIngredient1", strIngredient1);
                model.addAttribute("strIngredient2", strIngredient2);
                model.addAttribute("strIngredient3", strIngredient3);
                model.addAttribute("strIngredient4", strIngredient4);
                model.addAttribute("strIngredient5", strIngredient5);
                model.addAttribute("strIngredient6", strIngredient6);
                model.addAttribute("strIngredient7", strIngredient7);
                model.addAttribute("strIngredient8", strIngredient8);
                model.addAttribute("strIngredient9", strIngredient9);
                model.addAttribute("strIngredient10", strIngredient10);
                model.addAttribute("strIngredient11", strIngredient11);
                model.addAttribute("strIngredient12", strIngredient12);
                model.addAttribute("strIngredient13", strIngredient13);
                model.addAttribute("strIngredient14", strIngredient14);
                model.addAttribute("strIngredient15", strIngredient15);
                model.addAttribute("strIngredient16", strIngredient16);
                model.addAttribute("strIngredient17", strIngredient17);
                model.addAttribute("strIngredient18", strIngredient18);
                model.addAttribute("strIngredient19", strIngredient19);
                model.addAttribute("strIngredient20", strIngredient20);
                model.addAttribute("strMeasure1", strMeasure1);
                model.addAttribute("strMeasure2", strMeasure2);
                model.addAttribute("strMeasure3", strMeasure3);
                model.addAttribute("strMeasure4", strMeasure4);
                model.addAttribute("strMeasure5", strMeasure5);
                model.addAttribute("strMeasure6", strMeasure6);
                model.addAttribute("strMeasure7", strMeasure7);
                model.addAttribute("strMeasure8", strMeasure8);
                model.addAttribute("strMeasure9", strMeasure9);
                model.addAttribute("strMeasure10", strMeasure10);
                model.addAttribute("strMeasure11", strMeasure11);
                model.addAttribute("strMeasure12", strMeasure12);
                model.addAttribute("strMeasure13", strMeasure13);
                model.addAttribute("strMeasure14", strMeasure14);
                model.addAttribute("strMeasure15", strMeasure15);
                model.addAttribute("strMeasure16", strMeasure16);
                model.addAttribute("strMeasure17", strMeasure17);
                model.addAttribute("strMeasure18", strMeasure18);
                model.addAttribute("strMeasure19", strMeasure19);
                model.addAttribute("strMeasure20", strMeasure20);

                recipeLikes = recipeRepository.recipeLikes(id);
                model.addAttribute("recipeLikes", recipeLikes);

                recipeLiked = recipeRepository.findRecipeLiked(user.getId(), idMeal);
                model.addAttribute("recipeLiked", recipeLiked);

                recipeHidden = recipeRepository.findRecipeHidden(user.getId(), idMeal);
                model.addAttribute("recipeHidden", recipeHidden);

                Optional<Recipe> existingRecipe = Optional.ofNullable(recipeRepository.findByIdMeal(Long.parseLong(idMeal)));
                if (!existingRecipe.isPresent()) {
                    Recipe newRecipe = new Recipe();
                    newRecipe.setIdMeal(Long.parseLong(idMeal));
                    newRecipe.setStrMeal(strMeal);
                    newRecipe.setStrCategory(strCategory);
                    newRecipe.setStrMealThumb(strMealThumb);
                    recipeRepository.save(newRecipe);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "showRecipe";

    }

    private List<Ingredient> getIngredientsForRecipe(int recipeId){
        String jsonResponse = null;

        List<Ingredient> ingredients = new ArrayList<>();

        try {
            System.out.println(apiKey);
            URL url = new URL("https://www.themealdb.com/api/json/v2/1/lookup.php?i=" + recipeId);
//            URL url = new URL("https://www.themealdb.com/api/json/v2/" + apiKey + "/lookup.php?i=" + id);
//            URL url = new URL("https://www.themealdb.com/api/json/v2/" + apiKey + "/lookup.php?i=52814");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestMethod("GET");
            connection.getResponseCode();
            jsonResponse = new String(connection.getInputStream().readAllBytes());
            System.out.println("HTTP response code is " + connection.getResponseCode());
            System.out.println(jsonResponse);

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
            System.out.println(jsonObject);
            JsonArray mealsArray = JsonParser.parseString(jsonResponse).getAsJsonObject().getAsJsonArray("meals");
            System.out.println(mealsArray);
            for (JsonElement mealElement : mealsArray) {
                JsonObject mealObject = mealElement.getAsJsonObject();
                String idMeal = mealObject.get("idMeal").getAsString();
                String strMeal = mealObject.get("strMeal").getAsString();
                String strCategory = mealObject.get("strCategory").getAsString();
                String strInstructions = mealObject.get("strInstructions").getAsString();
                String strMealThumb = mealObject.get("strMealThumb").getAsString();


                String strIngredient1 = "";
                Ingredient ingredient1 = null;
                if (!mealObject.get("strIngredient1").isJsonNull()) {
                    strIngredient1 = mealObject.get("strIngredient1").getAsString();
                    if (!strIngredient1.isEmpty()) {
                        ingredient1 = ingredientRepository.findByName(strIngredient1.toLowerCase()).get(0);
                        ingredients.add(ingredient1);
                    }
                }

                String strIngredient2 = "";
                Ingredient ingredient2 = null;
                if (!mealObject.get("strIngredient2").isJsonNull()) {
                    strIngredient2 = mealObject.get("strIngredient2").getAsString();
                    if (!strIngredient2.isEmpty()) {
                        ingredient2 = ingredientRepository.findByName(strIngredient2.toLowerCase()).get(0);
                        ingredients.add(ingredient2);
                    }
                }

                String strIngredient3 = "";
                Ingredient ingredient3 = null;
                if (!mealObject.get("strIngredient3").isJsonNull()) {
                    strIngredient3 = mealObject.get("strIngredient3").getAsString();
                    if (!strIngredient3.isEmpty()) {
                        ingredient3 = ingredientRepository.findByName(strIngredient3.toLowerCase()).get(0);
                        ingredients.add(ingredient3);
                    }
                }

                String strIngredient4 = "";
                Ingredient ingredient4 = null;
                if (!mealObject.get("strIngredient4").isJsonNull()) {
                    strIngredient4 = mealObject.get("strIngredient4").getAsString();
                    if (!strIngredient4.isEmpty()) {
                        ingredient4 = ingredientRepository.findByName(strIngredient4.toLowerCase()).get(0);
                        ingredients.add(ingredient4);
                    }
                }

                String strIngredient5 = "";
                Ingredient ingredient5 = null;
                if (!mealObject.get("strIngredient5").isJsonNull()) {
                    strIngredient5 = mealObject.get("strIngredient5").getAsString();
                    if (!strIngredient5.isEmpty()) {
                        ingredient5 = ingredientRepository.findByName(strIngredient5.toLowerCase()).get(0);
                        ingredients.add(ingredient5);
                    }
                }

                String strIngredient6 = "";
                Ingredient ingredient6 = null;
                if (!mealObject.get("strIngredient6").isJsonNull()) {
                    strIngredient6 = mealObject.get("strIngredient6").getAsString();
                    if (!strIngredient6.isEmpty()) {
                        ingredient6 = ingredientRepository.findByName(strIngredient6.toLowerCase()).get(0);
                        ingredients.add(ingredient6);
                    }
                }
                String strIngredient7 = "";
                Ingredient ingredient7 = null;
                if (!mealObject.get("strIngredient7").isJsonNull()) {
                    strIngredient7 = mealObject.get("strIngredient7").getAsString();
                    if (!strIngredient7.isEmpty()) {
                        ingredient7 = ingredientRepository.findByName(strIngredient7.toLowerCase()).get(0);
                        ingredients.add(ingredient7);
                    }
                }
                String strIngredient8 = "";
                Ingredient ingredient8 = null;
                if (!mealObject.get("strIngredient8").isJsonNull()) {
                    strIngredient8 = mealObject.get("strIngredient8").getAsString();
                    if (!strIngredient8.isEmpty()) {
                        ingredient8 = ingredientRepository.findByName(strIngredient8.toLowerCase()).get(0);
                        ingredients.add(ingredient8);
                    }
                }
                String strIngredient9 = "";
                Ingredient ingredient9 = null;
                if (!mealObject.get("strIngredient1").isJsonNull()) {
                    strIngredient9 = mealObject.get("strIngredient9").getAsString();
                    if (!strIngredient9.isEmpty()) {
                        ingredient9 = ingredientRepository.findByName(strIngredient9.toLowerCase()).get(0);
                        ingredients.add(ingredient9);
                    }
                }
                String strIngredient10 = "";
                Ingredient ingredient10 = null;
                if (!mealObject.get("strIngredient10").isJsonNull()) {
                    strIngredient10 = mealObject.get("strIngredient10").getAsString();
                    if (!strIngredient10.isEmpty()) {
                        ingredient10 = ingredientRepository.findByName(strIngredient10.toLowerCase()).get(0);
                        ingredients.add(ingredient10);
                    }
                }
                String strIngredient11 = "";
                Ingredient ingredient11 = null;
                if (!mealObject.get("strIngredient11").isJsonNull()) {
                    strIngredient11 = mealObject.get("strIngredient11").getAsString();
                    if (!strIngredient11.isEmpty()) {
                        ingredient11 = ingredientRepository.findByName(strIngredient11.toLowerCase()).get(0);
                        ingredients.add(ingredient11);
                    }
                }
                String strIngredient12 = "";
                Ingredient ingredient12 = null;
                if (!mealObject.get("strIngredient12").isJsonNull()) {
                    strIngredient12 = mealObject.get("strIngredient12").getAsString();
                    if (!strIngredient12.isEmpty()) {
                        ingredient12 = ingredientRepository.findByName(strIngredient12.toLowerCase()).get(0);
                        ingredients.add(ingredient12);
                    }
                }
                String strIngredient13 = "";
                Ingredient ingredient13 = null;
                if (!mealObject.get("strIngredient13").isJsonNull()) {
                    strIngredient13 = mealObject.get("strIngredient13").getAsString();
                    if (!strIngredient13.isEmpty()) {
                        ingredient13 = ingredientRepository.findByName(strIngredient13.toLowerCase()).get(0);
                        ingredients.add(ingredient13);
                    }
                }
                String strIngredient14 = "";
                Ingredient ingredient14 = null;
                if (!mealObject.get("strIngredient14").isJsonNull()) {
                    strIngredient14 = mealObject.get("strIngredient14").getAsString();
                    if (!strIngredient14.isEmpty()) {
                        ingredient14 = ingredientRepository.findByName(strIngredient14.toLowerCase()).get(0);
                        ingredients.add(ingredient14);
                    }
                }
                String strIngredient15 = "";
                Ingredient ingredient15 = null;
                if (!mealObject.get("strIngredient15").isJsonNull()) {
                    strIngredient15 = mealObject.get("strIngredient15").getAsString();
                    if (!strIngredient15.isEmpty()) {
                        ingredient15 = ingredientRepository.findByName(strIngredient15.toLowerCase()).get(0);
                        ingredients.add(ingredient15);
                    }
                }
                String strIngredient16 = "";
                Ingredient ingredient16 = null;
                if (!mealObject.get("strIngredient16").isJsonNull()) {
                    strIngredient16 = mealObject.get("strIngredient16").getAsString();
                    if (!strIngredient16.isEmpty()) {
                        ingredient16 = ingredientRepository.findByName(strIngredient16.toLowerCase()).get(0);
                        ingredients.add(ingredient16);
                    }
                }
                String strIngredient17 = "";
                Ingredient ingredient17 = null;
                if (!mealObject.get("strIngredient17").isJsonNull()) {
                    strIngredient17 = mealObject.get("strIngredient17").getAsString();
                    if (!strIngredient17.isEmpty()) {
                        ingredient17 = ingredientRepository.findByName(strIngredient17.toLowerCase()).get(0);
                        ingredients.add(ingredient17);
                    }
                }
                String strIngredient18 = "";
                Ingredient ingredient18 = null;
                if (!mealObject.get("strIngredient18").isJsonNull()) {
                    strIngredient18 = mealObject.get("strIngredient18").getAsString();
                    if (!strIngredient18.isEmpty()) {
                        ingredient18 = ingredientRepository.findByName(strIngredient18.toLowerCase()).get(0);
                        ingredients.add(ingredient18);
                    }
                }
                String strIngredient19 = "";
                Ingredient ingredient19 = null;
                if (!mealObject.get("strIngredient19").isJsonNull()) {
                    strIngredient19 = mealObject.get("strIngredient19").getAsString();
                    if (!strIngredient19.isEmpty()) {
                        ingredient19 = ingredientRepository.findByName(strIngredient19.toLowerCase()).get(0);
                        ingredients.add(ingredient19);
                    }
                }
                String strIngredient20 = "";
                Ingredient ingredient20 = null;
                if (!mealObject.get("strIngredient20").isJsonNull()) {
                    strIngredient20 = mealObject.get("strIngredient20").getAsString();
                    if (!strIngredient20.isEmpty()) {
                        ingredient20 = ingredientRepository.findByName(strIngredient20.toLowerCase()).get(0);
                        ingredients.add(ingredient20);
                    }
                }

                ingredients.removeAll(Arrays.asList("", null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ingredients;
    }

    @GetMapping("/recipe/{recipeId}/ari")
    public String addRecipeIngredientsToGrocery(@PathVariable int recipeId, Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getReferenceById(loggedInUser.getId());
        List<Ingredient> usersGroceries = new ArrayList<>(user.getGroceryItems().stream().toList());
        System.out.println("userGrocery: "  + usersGroceries);
        List<Ingredient> recipeIngredients = getIngredientsForRecipe(recipeId);
        System.out.println("recipe ingreidnets: " + recipeIngredients);
//        System.out.println("hey it's the recipe's ingredients: " + ingredients);

//                only ingredients for that recipe
        try {
            if (usersGroceries.isEmpty()) {
                usersGroceries.addAll(recipeIngredients);
            } else {
                int i = 0;
                while (i < recipeIngredients.size()) {
                    System.out.println("hey");
                    if (!recipeIngredients.get(i).getName().equals(usersGroceries.get(i).getName())) {

                        usersGroceries.add(recipeIngredients.get(i));


                    }
                    System.out.println("hey2");
                    i++;
                }
            }

            user.setGroceryItems(usersGroceries);
            userDao.save(user);

            ingredients.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/recipe/" + recipeId;
    }
}
