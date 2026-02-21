import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
import java.math.BigDecimal;
import java.math.MathContext;

public class Main {

    static BigInteger decodeValue(String value, int base){
        return new BigInteger(value, base);
    }

static BigDecimal findConstant(BigInteger[] x, BigInteger[] y, int k){

    MathContext mc = new MathContext(50);
    BigDecimal c = BigDecimal.ZERO;

    for(int i=0;i<k;i++){

        BigDecimal term = new BigDecimal(y[i]);

        for(int j=0;j<k;j++){
            if(i!=j){
                BigDecimal numerator = new BigDecimal(x[j].negate());
                BigDecimal denominator = new BigDecimal(x[i].subtract(x[j]));
                term = term.multiply(numerator.divide(denominator, mc), mc);
            }
        }
        c = c.add(term, mc);
    }
    return c;
}
     public static void main(String[] args) throws Exception {

    String content = new String(Files.readAllBytes(Paths.get("input.json")));
    JSONObject obj = new JSONObject(content);

    JSONObject keys = obj.getJSONObject("keys");
    int n = keys.getInt("n");
    int k = keys.getInt("k");

    BigInteger[] x = new BigInteger[k];
    BigInteger[] y = new BigInteger[k];

    int index = 0;

    for(int i=1; i<=n && index<k; i++){

        String key = String.valueOf(i);

        if(obj.has(key)){

            JSONObject point = obj.getJSONObject(key);

            int base = Integer.parseInt(point.getString("base"));
            String value = point.getString("value");

            x[index] = new BigInteger(key);
            y[index] = decodeValue(value, base);
            index++;
        }
    }

    System.out.println("Points used:");
    for(int i=0;i<k;i++){
        System.out.println("(" + x[i] + ", " + y[i] + ")");
    }
    BigDecimal secret = findConstant(x, y, k);
    System.out.println("Secret (c) = " + secret.toBigInteger());
}
}
