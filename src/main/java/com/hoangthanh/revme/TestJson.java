package com.hoangthanh.revme;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestJson {
	public static void main(String[] args) {
		// JSON data string
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
        
        // Truy cập đến phần tử "content"
        JSONObject content = jsonObject.getJSONObject("content");
        
        System.out.println(content);

        // Lấy "goal_name"
        String goalName = content.getString("goal_name");
        System.out.println("Goal Name: " + goalName);

        // Lấy "days" (một mảng JSON)
        JSONArray days = content.getJSONArray("days");

        // Duyệt qua từng ngày trong mảng "days" và in ra thông tin
        for (int i = 0; i < days.length(); i++) {
            JSONObject day = days.getJSONObject(i);
            String nameDay = day.getString("name_day");
            String description = day.getString("description");
            int caloriesBurnedPerDay = day.getInt("calories_burned_per_day");
            int caloriesIntakePerDay = day.getInt("calories_intake_per_day");
            double waterIntakeTarget = day.getDouble("water_intake_target");

            System.out.println("\nDay: " + nameDay);
            System.out.println("Description: " + description);
            System.out.println("Calories burned per day: " + caloriesBurnedPerDay);
            System.out.println("Calories intake per day: " + caloriesIntakePerDay);
            System.out.println("Water intake target: " + waterIntakeTarget);

            // Lấy các bài tập (workouts) cho từng ngày
            JSONArray workouts = day.getJSONArray("workouts");
            System.out.println("Workouts:");
            for (int j = 0; j < workouts.length(); j++) {
                JSONObject workout = workouts.getJSONObject(j);
                int exerciseId = workout.getInt("exercise_id");
                String note = workout.getString("note");
                int status = workout.getInt("status");
                System.out.println("  Exercise ID: " + exerciseId + ", Note: " + note + ", Status: " + status);
            }

            // Lấy các bữa ăn (meals) cho từng ngày
            JSONArray meals = day.getJSONArray("meals");
            System.out.println("Meals:");
            for (int k = 0; k < meals.length(); k++) {
                JSONObject meal = meals.getJSONObject(k);
                int foodId = meal.getInt("food_id");
                String mealNote = meal.getString("note");
                int mealStatus = meal.getInt("status");
                System.out.println("  Food ID: " + foodId + ", Note: " + mealNote + ", Status: " + mealStatus);
            }
        }
	}
}
