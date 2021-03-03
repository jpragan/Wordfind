
import java.util.*;
import com.google.gson.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class WordFind {
    public static void main(String[] args) {
         String json = "{" +
                "    \"statusCode\": 200," +
                "    \"headers\": {" +
                "        \"Words Checked\": \"Nine\"" +
                "    }," +
                "    \"body\": {" +
                "        \"Words\": [" +
                "            {" +
                "                \"Word\": \"Test\"," +
                "                \"is_word\": true" +
                "            }," +
                "            {" +
                "                \"Word\": \"4er4\"," +
                "                \"is_word\": false" +
                "            }," +
                "            {" +
                "                \"Word\": \"Red\"," +
                "                \"is_word\": true" +
                "            }]}}";

        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        System.out.println(jsonObject);

        JsonArray wordName = jsonObject.getAsJsonObject("body").get("Words").getAsJsonArray();
        System.out.println(wordName);

        for (int i = 0; i < wordName.size(); i++) {
            JsonElement Words = wordName.get(i).getAsJsonObject().get("Word");
            JsonElement isWords = wordName.get(i).getAsJsonObject().get("is_word");
            if (isWords.getAsBoolean()){
                String word = Words.getAsString();
                word  = word.replace("\"","");
                System.out.println(word);
            }

        }


        // String to be tested for all english word permutations.
        String s = "mna";

        // Generate permutations
        Permutations.Permutation(s, "");

        // Initialize hashset for words verified to be english
        Set<String> english_Words = new HashSet<>();

        // Check all permutations in hash_set to see if they are english words.
        // If a word is found, it is added to the english_Words hashset.
        // System.out.println("All permutations:");
        //Permutations.hash_Set.forEach((j) -> System.out.println(j));
        System.out.println("All english words:");
        Permutations.hash_Set.forEach((n) -> {
                    try {
                        if (Dictionary.isEnglishWord(n)) {
                            english_Words.add(n);
                        }
                    } catch (Exception e) {
                        System.out.println("Error connecting to Dictionary.");
                        e.printStackTrace();
                    }
                }
        );
      // Prints list of confirmed english words.
         english_Words.forEach((n) -> System.out.println(n));
    }
}

final class Dictionary {

    public static Boolean isEnglishWord(String word) throws Exception {

        //Creating a HttpClient object
       // CloseableHttpClient httpclient = HttpClients.createDefault();

        //Creating a HttpGet object
     //   HttpGet httpget = new HttpGet("https://mfx2qgboid.execute-api.us-east-2.amazonaws.com/dictionary/checkword?word=" + word);

        //Executing the Get request
       // HttpResponse httpresponse = httpclient.execute(httpget);

        //Reading in the response
       // Scanner sc = new Scanner(httpresponse.getEntity().getContent());

        //converting scanner object to String
       // String response = sc.nextLine();

        //Parsing String into JSON object
        //JsonObject json = new JsonParser().parse(response).getAsJsonObject();

        //Converting JSON Object into Boolean
      //  boolean isWord = json.get("is_word").getAsBoolean();
        boolean isWord =  true;
        return isWord;
    }

}

class Permutations {

    static Set<String> hash_Set = new HashSet<>();

    // Recursive function to generate permutations of the string

    static void Permutation(String str, String ans) {

   // System.out.println("(" +str + ", " + ans + ")");
        // If string is empty
        if (str.length() == 0) {

            // Add the generated permutation to the
            // set in order to avoid duplicates
            hash_Set.add(ans);
       // System.out.println("1Just added ans - " + ans);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            //System.out.println("i = " + i);
            // start at the ith character of str
            char ch = str.charAt(i);
            if(!ans.equals("")) {
                hash_Set.add(ans);
              //  System.out.println("2Just added ans - " + ans);
            }
            // Rest of the string after, excluding
            // the ith character
            String ros = str.substring(0, i)
                    + str.substring(i + 1);
            if(!ros.equals("")) {
              //  System.out.println("3Just added ros - " + ros);
                hash_Set.add(ros);
            }
            //    System.out.println("(" +ros + ", " + ans + ch + ")");
            //System.out.println("--------------------");
            // Recursive call
            Permutation(ros, ans + ch);
        }
    }
}







