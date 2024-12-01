package petSitter.utils;

import org.testng.annotations.DataProvider;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.UserDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {


    @DataProvider(name="registerNegativeFromCSV")
    public static Iterator<Object[]> registerNegativeFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/data_csv/register.csv"));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(",");

            AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                    .email(System.currentTimeMillis()+split[0])
                    .password(split[1])
                    .firstName(split[2])
                    .lastName(split[3])
                    .description(split[4])
                    .photo(split[5])
                    .build();

            list.add(new Object[]{requestDTO});
        }

        reader.close();
        return list.iterator();
    }

   //resources/data_csv/registerWithInvalidEmail.csv
  // registerWithInvalidEmailNegativeFromCSV

    @DataProvider(name="registerWithInvalidEmailNegativeFromCSV")
    public static Iterator<Object[]> registerWithInvalidEmailNegativeFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/data_csv/registerWithInvalidEmail.csv"));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(",");

            AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                    .email(System.currentTimeMillis()+split[0])
                    .password(split[1])
                    .firstName(split[2])
                    .lastName(split[3])
                    .description(split[4])
                    .photo(split[5])
                    .build();

            list.add(new Object[]{requestDTO});
        }

        reader.close();
        return list.iterator();
    }
//registerWithInvalidPasswordNegativeFromCSV  registerWithInvalidPassword.csv
@DataProvider(name="registerWithInvalidPasswordNegativeFromCSV")
public static Iterator<Object[]> registerWithInvalidPasswordNegativeFromCSV() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/data_csv/registerWithInvalidPassword.csv"));

    String line;
    while ((line = reader.readLine()) != null) {
        String[] split = line.split(",");

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .email(System.currentTimeMillis()+split[0])
                .password(split[1])
                .firstName(split[2])
                .lastName(split[3])
                .description(split[4])
                .photo(split[5])
                .build();

        list.add(new Object[]{requestDTO});
    }

    reader.close();
    return list.iterator();
}
}
