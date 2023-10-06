import InOut.ReadingXLS;
import InOut.XlsWriter;
import comparators.*;
import enums.*;
import model.*;
import util.*;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<University> universities =
                ReadingXLS.readXlsUniversity("src/main/resources/universityInfo.xlsx");

        UniversityComparator universityComparator =
                ComparatorUtil.getUniversityComparator(UniversityComparatorType.YEAR);
        universities.sort(universityComparator);
        String universitiesJson = JsonUtil.universityListToJson(universities);

        System.out.println(universitiesJson);
        List<University> universitiesFromJson = JsonUtil.jsonToUniversityList(universitiesJson);

        System.out.println(universities.size() == universitiesFromJson.size());

        System.out.println();

        universities.forEach(university -> {
            String universityJson = JsonUtil.universityToJson(university);

            System.out.println(universityJson);
            University universityFromJson = JsonUtil.jsonToUniversity(universityJson);

            System.out.println(universityFromJson);
        });

        System.out.println();

        List<Student> students =
                ReadingXLS.readXlsStudents("src/main/resources/universityInfo.xlsx");
        StudentComparator studentComparator =
                ComparatorUtil.getStudentComparator(StudentComparatorType.AVG_EXAM_SCORE);
        students.sort(studentComparator);
        String studentsJson = JsonUtil.studentListToJson(students);

        System.out.println(studentsJson);
        List<Student> studentsFromJson = JsonUtil.jsonToStudentList(studentsJson);

        System.out.println(students.size() == studentsFromJson.size());

        System.out.println();

        students.forEach(student -> {
            String studentJson = JsonUtil.studentToJson(student);

            System.out.println(studentJson);
            Student studentFromJson = JsonUtil.jsonToStudent(studentJson);

            System.out.println(studentFromJson);
        });

        List<Statistics> statisticsList = StatisticsUtil.createStatistics(students, universities);
        XlsWriter.writeXlsStatistics(statisticsList, "statistics_1.xlsx");
    }
}

