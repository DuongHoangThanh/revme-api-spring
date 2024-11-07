package com.hoangthanh.revme.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hoangthanh.revme.models.Assessment;
import com.hoangthanh.revme.models.Exercise;
import com.hoangthanh.revme.models.Food;
import com.hoangthanh.revme.models.Goal;
import com.hoangthanh.revme.models.MealPlan;
import com.hoangthanh.revme.models.Plan;
import com.hoangthanh.revme.models.User;
import com.hoangthanh.revme.models.WorkoutPlan;
import com.hoangthanh.revme.repository.ExerciseRepository;
import com.hoangthanh.revme.repository.FoodRepository;
import com.hoangthanh.revme.repository.GoalRepository;
import com.hoangthanh.revme.repository.MealPlanRepository;
import com.hoangthanh.revme.repository.PlanRepository;
import com.hoangthanh.revme.repository.WorkoutPlanRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PlanService {
	
	@Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    private MealPlanRepository mealPlanRepository;
    
    @Autowired
    private ExerciseRepository exerciseRepository;
    
    @Autowired
    private FoodRepository foodRepository;

    @Value("${openai.api.key}")
    private String openaiApiKey;

    /**
     * Hàm gửi thông tin assessment, danh sách exercise và food lên API ChatGPT
     * và nhận về kế hoạch tập luyện và ăn uống.
     */
    public JSONObject generatePlan(Assessment assessment, List<Exercise> exercises, List<Food> foods) {
    	
    	// Lấy thông tin Id và Name của Exercise và Food
    	List<Map<String, Object>> exerciseList = exercises.stream()
    	        .map(ex -> {
    	            Map<String, Object> map = new HashMap<>();
    	            map.put("id", ex.getId());
    	            map.put("name", ex.getName());
    	            return map;
    	        })
    	        .collect(Collectors.toList());

    	List<Map<String, Object>> foodList = foods.stream()
    	        .map(fd -> {
    	            Map<String, Object> map = new HashMap<>();
    	            map.put("id", fd.getId());
    	            map.put("name", fd.getName());
    	            return map;
    	        })
    	        .collect(Collectors.toList());

        // Tạo prompt để gửi đến API ChatGPT
        String prompt = "- Dựa trên thông tin của người dùng sau, hãy tạo một lộ trình tập luyện và ăn uống trong 7 ngày dưới dạng JSON. \n"
        		+"- Assessment: " + assessment + "\n" +
                "- Danh sách bài tập:\n" + exerciseList.toString() + "\n" +
                "- Danh sách thực phẩm:\n" + foodList.toString() + "\n" +
                "- Chỉ dựa vào danh sách bài tập và thực phẩm trên trả về kết quả dưới dạng JSON với cấu trúc như sau:\n" +
                "{\n" +
                " \"goal_name\": \"Giảm cân\",\n"+
                "  \"days\": [\n" +
                "    {\n" +
                "      \"name_day\": \"Ngày 1\",\n" +
                "      \"description\": \"Mô tả ngắn về kế hoạch trong ngày\",\n" +
                "      \"calories_burned_per_day\": 500,\n" +
                "      \"calories_intake_per_day\": 2000,\n" +
                "      \"water_intake_target\": 2.0,\n" +
                "      \"workouts\": [\n" +
                "        {\"exercise_id\": 1, \"note\": \"Ghi chú bài tập\", \"status\": 0},…\n" +
                "      ],\n" +
                "      \"meals\": [\n" +
                "        {\"food_id\": 1, \"note\": \"Ghi chú bữa ăn\", \"status\": 0},…\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";
        
        System.out.println(prompt);
        System.out.println("Successful!");

        // Cấu hình header và body cho request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + openaiApiKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", 1000);
        requestBody.put("temperature", 0.7);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        // Gọi API ChatGPT và nhận JSON response
        ResponseEntity<String> response = restTemplate.postForEntity("https://api.openai.com/v1/completions", entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            // Chuyển đổi dữ liệu nhận về thành JSONObject
            return new JSONObject(response.getBody());
        } else {
            throw new RuntimeException("Error occurred while calling ChatGPT API: " + response.getStatusCode());
        }
    }

    /**
     * Hàm nhận dữ liệu JSON từ API ChatGPT và lưu thông tin vào cơ sở dữ liệu.
     */
    public void saveGeneratedPlan(JSONObject generatedData, User user) {
        // Lấy goal_name từ dữ liệu nhận về
        String goalName = generatedData.getString("goal_name");

        // Tạo một Goal mới và lưu vào database
        Goal newGoal = new Goal();
        newGoal.setGoalName(goalName);
        newGoal.setUser(user);
        goalRepository.save(newGoal);

        // Lấy danh sách các ngày từ JSON response
        JSONArray days = generatedData.getJSONArray("days");

        // Lưu các kế hoạch từng ngày vào bảng Plan
        for (int i = 0; i < days.length(); i++) {
            JSONObject day = days.getJSONObject(i);

            // Tạo Plan mới
            Plan plan = new Plan();
            plan.setNameDay(day.getString("name_day"));
            plan.setDescription(day.getString("description"));
            plan.setCaloriesBurnedPerDay(day.getDouble("calories_burned_per_day"));
            plan.setCaloriesIntakePerDay(day.getDouble("calories_intake_per_day"));
            plan.setWaterIntakeTarget(day.getDouble("water_intake_target"));
            plan.setGoal(newGoal);
            plan.setUser(user);
            planRepository.save(plan);

            // Lưu thông tin WorkoutPlan
            JSONArray workouts = day.getJSONArray("workouts");
            for (int j = 0; j < workouts.length(); j++) {
                JSONObject workout = workouts.getJSONObject(j);
                
                WorkoutPlan workoutPlan = new WorkoutPlan();
                
                long exerciseId = workout.getLong("exercise_id");
                Optional<Exercise> exerciseOpt = exerciseRepository.findById(exerciseId);
                
                if (exerciseOpt.isPresent()) {
                    Exercise exercise = exerciseOpt.get();
                    workoutPlan.setExercise(exercise);
                } else {
                	throw new EntityNotFoundException("Exercise not found with ID: " + exerciseId);
                }
                
                workoutPlan.setNote(workout.getString("note"));
                
                int statusInt = workout.getInt("status");
                boolean status = (statusInt == 1);
                
                workoutPlan.setStatus(status);
                workoutPlan.setPlan(plan);
                workoutPlanRepository.save(workoutPlan);
            }

            // Lưu thông tin MealPlan
            JSONArray meals = day.getJSONArray("meals");
            for (int k = 0; k < meals.length(); k++) {
                JSONObject meal = meals.getJSONObject(k);

                MealPlan mealPlan = new MealPlan();
                
                long foodId = meal.getLong("food_id");
                Optional<Food> foodOpt = foodRepository.findById(foodId);
                
                if (foodOpt.isPresent()) {
                    Food food = foodOpt.get();
                    mealPlan.setFood(food);
                } else {
                	throw new EntityNotFoundException("Food not found with ID: " + foodId);
                }
                
                mealPlan.setNote(meal.getString("note"));
                
                int statusInt = meal.getInt("status");
                boolean status = (statusInt == 1);  
                mealPlan.setStatus(status);
                
                mealPlan.setPlan(plan);
                mealPlanRepository.save(mealPlan);
            }
        }
    }
}
