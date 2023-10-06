package InOut;

import enums.StudyProfile;
import model.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadingXLS {
    private ReadingXLS() {
    }
    public static List<Student> readXlsStudents(String path) throws IOException {
        List<Student> students = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(path);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet studientSheet = workbook.getSheet("Студенты");

        Iterator<Row> rows = studientSheet.iterator();
        rows.next();

        while (rows.hasNext()) {
            Row currentRow = rows.next();
            Student student = new Student();
            students.add(student);
            student.setUniversityId(currentRow.getCell(0).getStringCellValue());
            student.setFullName(currentRow.getCell(1).getStringCellValue());
            student.setCurrentCourseNumber((int) currentRow.getCell(2).getNumericCellValue());
            student.setAvgExamScore((float) currentRow.getCell(3).getNumericCellValue());
        }
        return students;
    }

    public static List<University> readXlsUniversity(String path) throws IOException {
        List<University> universities = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(path);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet universitySheet = workbook.getSheet("Университеты");

        Iterator<Row> rows = universitySheet.iterator();
        rows.next();

        while (rows.hasNext()) {
            Row currentRow = rows.next();
            University university = new University();
            universities.add(university);
            university.setId(currentRow.getCell(0).getStringCellValue());
            university.setFullName(currentRow.getCell(1).getStringCellValue());
            university.setShortName(currentRow.getCell(2).getStringCellValue());
            university.setYearOfFoundation((int) currentRow.getCell(3).getNumericCellValue());
            university.setMainProfile(StudyProfile.valueOf(
                    StudyProfile.class, currentRow.getCell(4).getStringCellValue()));
        }

        return universities;
    }
}

