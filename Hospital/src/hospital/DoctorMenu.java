public void schedule(String doctorID) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;  // Declare the date variable outside the try block

        while (date == null) {
            System.out.println("Enter date (yyyy-MM-dd): ");
            String Available_date = scanner.nextLine();  // Corrected variable name

            try {
                // Parse the string into a Date object
                date = dateFormat.parse(Available_date);  // Assign value to date
                System.out.println("Parsed Date: " + date);

            } catch (ParseException e) {
                System.out.println("Invalid date format! Please try again.");
            }
        }
        
        // Now that date is valid, ask for the time
        System.out.println("Enter time (e.g., 9:00 AM, 1:00 PM, 4:00 PM): ");
        String Available_Time = scanner.nextLine();  // Use nextLine() to get full input including spaces
        
        // Create the appointment using the parsed date
        Appointment appointment = new Appointment("Empty", doctorID, date, Available_Time);
        calendar.add(appointment);
        System.out.println("Appointment scheduled in your calendar.");
    }
