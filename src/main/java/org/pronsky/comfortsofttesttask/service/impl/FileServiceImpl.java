package org.pronsky.comfortsofttesttask.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.pronsky.comfortsofttesttask.service.FileService;
import org.pronsky.comfortsofttesttask.service.dto.FindMaxDto;
import org.pronsky.comfortsofttesttask.service.exceptions.FailedToReadFileException;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private static final int NUMBER_OF_SHEET = 0;
    private static final int COLUMN_INDEX = 0;

    @Override
    public Long getMax(FindMaxDto findMaxDto) {
        return getMaxNumberFromArray(readNumbers(findMaxDto));
    }

    private Long getMaxNumberFromArray(long[] array) {
        long max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private long[] readNumbers(FindMaxDto findMaxDto) {
        int n = findMaxDto.getN();
        long[] numbers = new long[n];
        try (FileInputStream fileInputStream = new FileInputStream(findMaxDto.getPath())) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(NUMBER_OF_SHEET);
            for (int i = 0; i < n; i++) {
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(COLUMN_INDEX);
                if (cell.getCellType() == CellType.NUMERIC) {
                    numbers[i] = (long) cell.getNumericCellValue();
                }
            }
            workbook.close();
        } catch (IOException e) {
            throw new FailedToReadFileException();
        }
        return numbers;
    }
}
