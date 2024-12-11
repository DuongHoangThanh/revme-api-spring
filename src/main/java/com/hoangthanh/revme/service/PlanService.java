package com.hoangthanh.revme.service;

import java.time.LocalDate;
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
import com.hoangthanh.revme.models.Progress;
import com.hoangthanh.revme.models.User;
import com.hoangthanh.revme.models.WorkoutPlan;
import com.hoangthanh.revme.repository.ExerciseRepository;
import com.hoangthanh.revme.repository.FoodRepository;
import com.hoangthanh.revme.repository.GoalRepository;
import com.hoangthanh.revme.repository.MealPlanRepository;
import com.hoangthanh.revme.repository.PlanRepository;
import com.hoangthanh.revme.repository.ProgressRepository;
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
	
	@Autowired
	private ProgressRepository progressRepository;

	@Value("${openai.api.key}")
	private String openaiApiKey;

	public JSONObject generatePlan(Assessment assessment, List<Exercise> exercises, List<Food> foods) {

		List<Map<String, Object>> exerciseList = exercises.stream().map(ex -> {
			Map<String, Object> map = new HashMap<>();
			map.put("id", ex.getId());
			map.put("name", ex.getName());
			return map;
		}).collect(Collectors.toList());

		List<Map<String, Object>> foodList = foods.stream().map(fd -> {
			Map<String, Object> map = new HashMap<>();
			map.put("id", fd.getId());
			map.put("name", fd.getName());
			return map;
		}).collect(Collectors.toList());

		String prompt = "- Dựa trên thông tin của người dùng sau, hãy tạo một lộ trình tập luyện và ăn uống trong 7 ngày dưới dạng JSON. \n"
				+ "- Assessment: " + assessment + "\n" + "- Danh sách bài tập:\n" + exerciseList.toString() + "\n"
				+ "- Danh sách thực phẩm:\n" + foodList.toString() + "\n"
				+ "- Chỉ dựa vào danh sách bài tập và thực phẩm trên trả về kết quả dưới dạng JSON với cấu trúc như sau, chỉ trả về dạng JSON sau và không có thêm bất cứ lời nhắc nào:\n"
				+ "{\n" + " \"goal_name\": \"Giảm cân\",\n" + "  \"days\": [\n" + "    {\n"
				+ "      \"name_day\": \"Ngày 1\",\n"
				+ "      \"description\": \"Mô tả ngắn về kế hoạch trong ngày\",\n"
				+ "      \"calories_burned_per_day\": 500,\n" + "      \"calories_intake_per_day\": 2000,\n"
				+ "      \"water_intake_target\": 2.0,\n" + "      \"workouts\": [\n"
				+ "        {\"exercise_id\": 1, \"note\": \"Ghi chú bài tập\", \"status\": 0},…\n" + "      ],\n"
				+ "      \"meals\": [\n" + "        {\"food_id\": 1, \"note\": \"Ghi chú bữa ăn\", \"status\": 0},…\n"
				+ "      ]\n" + "    }\n" + "  ]\n" + "}\n";

		System.out.println(prompt);
		System.out.println("Successful!");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + openaiApiKey);

		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("model", "gpt-4o");
		requestBody.put("messages", new Object[] { Map.of("role", "system", "content", "You are a helpful assistant."),
				Map.of("role", "user", "content", prompt) });
		requestBody.put("temperature", 1.0);
		requestBody.put("max_tokens", 4095);
		requestBody.put("top_p", 1);
		requestBody.put("frequency_penalty", 0);
		requestBody.put("presence_penalty", 0);

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

		// Gọi API ChatGPT và nhận JSON response

 		ResponseEntity<String> response = restTemplate.postForEntity("https://api.openai.com/v1/chat/completions",
				entity, String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
//			return new JSONObject(response.getBody());
			String responseBody = response.getBody();
			JSONObject jsonResponse = new JSONObject(responseBody); // Khai báo và khởi tạo jsonResponse ở đây

			// Lấy phần 'content' từ cấu trúc JSON
			String content = jsonResponse.getJSONArray("choices").getJSONObject(0) // Lấy phần tử đầu tiên trong mảng
					.getJSONObject("message") // Lấy đối tượng 'message'
					.getString("content"); // Lấy phần 'content' trong 'message'
			
			String cleanedContent = content
		            .replaceAll("^```json", "")  // Loại bỏ ```json ở đầu
		            .replaceAll("```$", "")      // Loại bỏ ``` ở cuối
		            .trim();
			
			// Chuyển cleanedContent thành JSONObject
//	        JSONObject data = new JSONObject(cleanedContent);
			
			System.out.println(content);

			// Chuyển content thành JSONObject (nếu cần)
			JSONObject generatedData = new JSONObject();
			generatedData.put("content", cleanedContent);
			System.out.println(generatedData);
			
			
			String jsonString = "{ \"content\": {\n"
	        		+ "  \"goal_name\":\"Giảm cân\",\n"
	        		+ "  \"days\": [\n"
	        		+ "    {\n"
	        		+ "      \"name_day\": \"Ngày 1\",\n"
	        		+ "      \"description\": \"Tập trung vào cardio và các bài tập toàn thân.\",\n"
	        		+ "      \"calories_burned_per_day\": 500,\n"
	        		+ "      \"calories_intake_per_day\": 2000,\n"
	        		+ "      \"water_intake_target\": 2.0,\n"
	        		+ "      \"workouts\": [\n"
	        		+ "        {\"exercise_id\": 1, \"note\": \"Thực hiện 3 sets, mỗi set 15 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 3, \"note\": \"Thực hiện 3 sets, mỗi set 15 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 16, \"note\": \"Thực hiện trong 5 phút\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 7, \"note\": \"Tập trong 10 phút\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 8, \"note\": \"Thực hiện 3 sets, mỗi set 30 giây\", \"status\": 0}\n"
	        		+ "      ],\n"
	        		+ "      \"meals\": [\n"
	        		+ "        {\"food_id\": 19, \"note\": \"Tàu hũ xào rau cho bữa sáng\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 6, \"note\": \"Ăn như bữa trưa\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 16, \"note\": \"Trộn salad với dầu olive\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 12, \"note\": \"Yến mạch ăn sáng kèm trái cây\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 13, \"note\": \"Chuối cho bữa phụ\", \"status\": 0}\n"
	        		+ "      ]\n"
	        		+ "    },\n"
	        		+ "    {\n"
	        		+ "      \"name_day\": \"Ngày 2\",\n"
	        		+ "      \"description\": \"Chú trọng vào luyện tập sức mạnh cơ bắp.\",\n"
	        		+ "      \"calories_burned_per_day\": 500,\n"
	        		+ "      \"calories_intake_per_day\": 2000,\n"
	        		+ "      \"water_intake_target\": 2.0,\n"
	        		+ "      \"workouts\": [\n"
	        		+ "        {\"exercise_id\": 4, \"note\": \"Giữ tư thế trong 1 phút\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 9, \"note\": \"Thực hiện 3 sets, mỗi set 12 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 10, \"note\": \"Thực hiện 3 sets, mỗi set 12 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 11, \"note\": \"Thực hiện 3 sets, mỗi set 20 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 5, \"note\": \"Thực hiện 3 sets, mỗi set 12 lần mỗi chân\", \"status\": 0}\n"
	        		+ "      ],\n"
	        		+ "      \"meals\": [\n"
	        		+ "        {\"food_id\": 9, \"note\": \"Trứng luộc cho bữa sáng\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 5, \"note\": \"Cơm gạo lứt trộn rau\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 4, \"note\": \"Bông cải xào đậu nành\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 7, \"note\": \"Ăn kèm bơ với salad\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 21, \"note\": \"Quả việt quất ăn vặt\", \"status\": 0}\n"
	        		+ "      ]\n"
	        		+ "    },\n"
	        		+ "    {\n"
	        		+ "      \"name_day\": \"Ngày 3\",\n"
	        		+ "      \"description\": \"Phát triển sức chịu đựng và sự linh hoạt.\",\n"
	        		+ "      \"calories_burned_per_day\": 500,\n"
	        		+ "      \"calories_intake_per_day\": 2000,\n"
	        		+ "      \"water_intake_target\": 2.0,\n"
	        		+ "      \"workouts\": [\n"
	        		+ "        {\"exercise_id\": 13, \"note\": \"Thực hiện 3 sets, mỗi set 15 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 1, \"note\": \"Thực hiện 3 sets, mỗi set 15 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 20, \"note\": \"Giữ tư thế trong 30 giây mỗi bên\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 17, \"note\": \"Thực hiện trong 1 phút\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 18, \"note\": \"Thực hiện 3 sets, mỗi set 15 lần\", \"status\": 0}\n"
	        		+ "      ],\n"
	        		+ "      \"meals\": [\n"
	        		+ "        {\"food_id\": 11, \"note\": \"Sữa chua Hy Lạp với muesli\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 8, \"note\": \"Khoai lang nướng cho bữa trưa\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 16, \"note\": \"Salad cải bó xôi với dressing nhẹ\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 20, \"note\": \"Cam cho bữa phụ\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 10, \"note\": \"Hạnh nhân ăn vặt\", \"status\": 0}\n"
	        		+ "      ]\n"
	        		+ "    },\n"
	        		+ "    {\n"
	        		+ "      \"name_day\": \"Ngày 4\",\n"
	        		+ "      \"description\": \"Chạy đường dài để tăng cường sức bền.\",\n"
	        		+ "      \"calories_burned_per_day\": 500,\n"
	        		+ "      \"calories_intake_per_day\": 2000,\n"
	        		+ "      \"water_intake_target\": 2.0,\n"
	        		+ "      \"workouts\": [\n"
	        		+ "        {\"exercise_id\": 7, \"note\": \"Chạy dây trong 10 phút\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 2, \"note\": \"Thực hiện 3 sets, mỗi set 15 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 6, \"note\": \"Thực hiện 3 sets, mỗi set 10 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 14, \"note\": \"Thực hiện 3 sets, mỗi set 10 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 19, \"note\": \"Thực hiện số lần tối đa\", \"status\": 0}\n"
	        		+ "      ],\n"
	        		+ "      \"meals\": [\n"
	        		+ "        {\"food_id\": 1, \"note\": \"Carrot sticks với hummus\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 19, \"note\": \"Xáo đậu phụ với rau\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 12, \"note\": \"Porridge với trái cây\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 21, \"note\": \"Blueberries cho bữa trưa\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 15, \"note\": \"Quinoa salad cho bữa tối\", \"status\": 0}\n"
	        		+ "      ]\n"
	        		+ "    },\n"
	        		+ "    {\n"
	        		+ "      \"name_day\": \"Ngày 5\",\n"
	        		+ "      \"description\": \"Kết hợp các bài tập thể lực với vận động toàn thân.\",\n"
	        		+ "      \"calories_burned_per_day\": 500,\n"
	        		+ "      \"calories_intake_per_day\": 2000,\n"
	        		+ "      \"water_intake_target\": 2.0,\n"
	        		+ "      \"workouts\": [\n"
	        		+ "        {\"exercise_id\": 9, \"note\": \"Thực hiện 3 sets, mỗi set 15 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 10, \"note\": \"Thực hiện 3 sets, mỗi set 15 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 15, \"note\": \"Thực hiện 3 sets, mỗi set 10 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 5, \"note\": \"Thực hiện 3 sets, mỗi set 15 lần mỗi chân\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 3, \"note\": \"Thực hiện 3 sets, mỗi set 20 lần\", \"status\": 0}\n"
	        		+ "      ],\n"
	        		+ "      \"meals\": [\n"
	        		+ "        {\"food_id\": 6, \"note\": \"Apple cho bữa sáng\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 5, \"note\": \"Brown rice trộn rau\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 8, \"note\": \"Sweet potato hấp\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 4, \"note\": \"Broccoli steamned với chanh\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 20, \"note\": \"Oranges cho bữa xế\", \"status\": 0}\n"
	        		+ "      ]\n"
	        		+ "    },\n"
	        		+ "    {\n"
	        		+ "      \"name_day\": \"Ngày 6\",\n"
	        		+ "      \"description\": \"Tập trung vào luyện tập sức mạnh và một chút cardio.\",\n"
	        		+ "      \"calories_burned_per_day\": 500,\n"
	        		+ "      \"calories_intake_per_day\": 2000,\n"
	        		+ "      \"water_intake_target\": 2.0,\n"
	        		+ "      \"workouts\": [\n"
	        		+ "        {\"exercise_id\": 16, \"note\": \"Thực hiện trong 5 phút\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 11, \"note\": \"Thực hiện 3 sets, mỗi set 25 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 6, \"note\": \"Thực hiện 3 sets, mỗi set 12 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 19, \"note\": \"Thực hiện số lần tối đa\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 21, \"note\": \"Giữ tư thế trong 1 phút\", \"status\": 0}\n"
	        		+ "      ],\n"
	        		+ "      \"meals\": [\n"
	        		+ "        {\"food_id\": 9, \"note\": \"Egg scramble với rau\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 7, \"note\": \"Avocado spread cho bánh mì nướng\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 13, \"note\": \"Banana cho bữa phụ\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 4, \"note\": \"Broccoli salad cho bữa trưa\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 19, \"note\": \"Maple tofu cho bữa tối\", \"status\": 0}\n"
	        		+ "      ]\n"
	        		+ "    },\n"
	        		+ "    {\n"
	        		+ "      \"name_day\": \"Ngày 7\",\n"
	        		+ "      \"description\": \"Ngày nghỉ dưỡng đồng thời phục hồi cơ bắp.\",\n"
	        		+ "      \"calories_burned_per_day\": 500,\n"
	        		+ "      \"calories_intake_per_day\": 2000,\n"
	        		+ "      \"water_intake_target\": 2.0,\n"
	        		+ "      \"workouts\": [\n"
	        		+ "        {\"exercise_id\": 8, \"note\": \"Giữ tư thế trong 30 phút\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 20, \"note\": \"Side plank trong 1 phút mỗi bên\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 14, \"note\": \"Thực hiện 3 sets, mỗi set 12 lần\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 2, \"note\": \"Thực hiện số lần tối đa\", \"status\": 0},\n"
	        		+ "        {\"exercise_id\": 17, \"note\": \"Thực hiện trong 1 phút\", \"status\": 0}\n"
	        		+ "      ],\n"
	        		+ "      \"meals\": [\n"
	        		+ "        {\"food_id\": 12, \"note\": \"Oatmeal với trái cây\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 1, \"note\": \"Cà rốt sống\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 21, \"note\": \"Việt quất với sữa chua\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 11, \"note\": \"Greek yogurt cho bữa phụ\", \"status\": 0},\n"
	        		+ "        {\"food_id\": 15, \"note\": \"Quinoa salad cho bữa tối\", \"status\": 0}\n"
	        		+ "      ]\n"
	        		+ "    }\n"
	        		+ "  ]\n"
	        		+ "}}";

	        // Parse the JSON string to a JSONObject
	        JSONObject jsonObject = new JSONObject(jsonString);
			
			return generatedData;
		} else {
			throw new RuntimeException("Error occurred while calling ChatGPT API: " + response.getStatusCode());
		}
	}

	public void saveGeneratedPlan(JSONObject generatedData, User user) {

		if (generatedData.has("content")) {
			LocalDate currentDate = LocalDate.now();
			
			JSONObject content = null;
			try {
				// Lấy dữ liệu từ "content" dưới dạng chuỗi
			    String contentString = generatedData.getString("content");

			    // Chuyển đổi chuỗi JSON thành JSONObject
			    content = new JSONObject(contentString);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			String goalName = "";
			try {
				 goalName = content.getString("goal_name");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}			

			Goal newGoal = new Goal();
			newGoal.setGoalName(goalName);
			newGoal.setUser(user);
			goalRepository.save(newGoal);

			JSONArray days = content.getJSONArray("days");
			
			for (int i = 0; i < days.length(); i++) {
				JSONObject day = days.getJSONObject(i);

				Plan plan = new Plan();
				plan.setNameDay(day.getString("name_day"));
				plan.setDescription(day.getString("description"));
				plan.setCaloriesBurnedPerDay(day.getDouble("calories_burned_per_day"));
				plan.setCaloriesIntakePerDay(day.getDouble("calories_intake_per_day"));
				plan.setWaterIntakeTarget(day.getDouble("water_intake_target"));
				plan.setGoal(newGoal);
				plan.setUser(user);
				
				LocalDate specificDate = currentDate.plusDays(i); // Ngày hiện tại + i ngày
			    plan.setSpecificDate(specificDate);
			   
			    int totalWorkoutsTarget = 0;
			    int totalMealsTarget = 0;
				
				planRepository.save(plan);

				// add WorkoutPlan
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
					
					totalWorkoutsTarget++;
				}

				// add MealPlan
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
					
					totalMealsTarget++;
				}

				plan.setTotalWorkoutsTarget(totalWorkoutsTarget);
			    plan.setTotalMealsTarget(totalMealsTarget);
			    planRepository.save(plan);

			    // Add Progress
			    Progress progress = new Progress();
			    progress.setCompletedWorkouts(0);       
			    progress.setCompletedMeals(0);         
			    progress.setTotalCaloriesBurned(0.0);   
			    progress.setTotalCaloriesIntake(0.0);   
			    progress.setTotalWaterIntake(0.0);      
			    progress.setPlan(plan);                 

			    progressRepository.save(progress);
			}
		} else {
			System.out.println("Error!");
		}
	}
	
	public long getTotalPlan() {
		return planRepository.count();
	}
}
