import comparators.StudentComparator;
import comparators.UniversityComparator;
import enums.StudentComparatorType;
import enums.UniversityComparatorType;
import model.*;
import util.ComparatorUtil;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<University> universities =
                ReadingXLS.readXlsUniversity("src/main/resources/universityInfo.xlsx");
        UniversityComparator universityComparator =
                ComparatorUtil.getUniversityComparator(UniversityComparatorType.YEAR);
        universities.stream()
                .sorted(universityComparator)
                .forEach(System.out::println);

        System.out.println();

        List<Student> students =
                ReadingXLS.readXlsStudents("src/main/resources/universityInfo.xlsx");
        StudentComparator studentComparator =
                ComparatorUtil.getStudentComparator(StudentComparatorType.AVG_EXAM_SCORE);
        students.stream()
                .sorted(studentComparator)
                .forEach(System.out::println);
    }
}

