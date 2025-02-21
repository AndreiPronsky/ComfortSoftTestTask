package org.pronsky.comfortsofttesttask.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.pronsky.comfortsofttesttask.service.FileService;
import org.pronsky.comfortsofttesttask.service.dto.request.FindMaxDto;
import org.pronsky.comfortsofttesttask.service.dto.response.MaxValueResponseDto;
import org.pronsky.comfortsofttesttask.service.exceptions.FailedToReadFileException;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private static final int NUMBER_OF_SHEET = 0;
    private static final int COLUMN_INDEX = 0;

    @Override
    public MaxValueResponseDto getMaxNumber(FindMaxDto findMaxDto) {
        return MaxValueResponseDto.builder()
                .maxValue(getMaxNumberFromArray(readNumbersFromExcelFile(findMaxDto)))
                .build();
    }

    private Long getMaxNumberFromArray(long[] array) {
        mergeSort(array);
        return array[array.length - 1];
    }

    private long[] readNumbersFromExcelFile(FindMaxDto findMaxDto) {
        int n = findMaxDto.getNumberOfElements();
        long[] numbers = new long[n];
        try (FileInputStream fileInputStream = new FileInputStream(findMaxDto.getLocalPathToFile())) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(NUMBER_OF_SHEET);
            for (int i = 0; i < n; i++) {
                Row row = sheet.getRow(i);
                if (row == null || row.getPhysicalNumberOfCells() == 0) {
                    break;
                }
                Cell cell = row.getCell(COLUMN_INDEX);
                if (cell.getCellType() == CellType.NUMERIC) {
                    numbers[i] = (long) cell.getNumericCellValue();
                }
            }
            workbook.close();
        } catch (NullPointerException | IOException e) {
            log.error(e.getMessage(), e);
            throw new FailedToReadFileException();
        }
        return numbers;
    }

    private static void mergeSort(long[] array) {
        if (array == null || array.length < 2) return;
        mergeSort(array, 0, array.length - 1);
        log.info("Sorted array: {}", array);
    }

    private static void mergeSort(long[] array, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    private static void merge(long[] array, int left, int middle, int right) {
        int i = 0;
        int j = 0;
        int k = left;
        int n1 = middle - left + 1;
        int n2 = right - middle;
        long[] leftArray = new long[n1];
        long[] rightArray = new long[n2];
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, middle + 1, rightArray, 0, n2);

        while (i < n1 && j < n2) {
            array[k++] = (leftArray[i] <= rightArray[j]) ? leftArray[i++] : rightArray[j++];
        }
        while (i < n1) array[k++] = leftArray[i++];
        while (j < n2) array[k++] = rightArray[j++];
    }
}
