package org.example;
import java.io.File;
import java.io.FileWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileManager {
        // =========================
        // SAVE RECEIPT
        // =========================

        public static void saveReceipt(Order order) {

            try {

                // Create receipts folder
                File folder = new File("receipts");

                if (!folder.exists()) {

                    folder.mkdir();
                }

                // File name
                String fileName =
                        LocalDateTime.now()
                                .format(
                                        DateTimeFormatter
                                                .ofPattern(
                                                        "yyyyMMdd-HHmmss"));

                File receiptFile =
                        new File(folder,
                                fileName + ".txt");

                // Write receipt
                FileWriter writer =
                        new FileWriter(receiptFile);

                writer.write(
                        order.getOrderSummary());

                writer.close();

                System.out.println(
                        "\n✅ Receipt saved successfully!");

            } catch (Exception e) {

                System.out.println(
                        "\n❌ Error saving receipt.");
            }
        }
    }

