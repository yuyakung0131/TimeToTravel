if ($('#from, #to').length) {
    // check if element is available to bind ITS ONLY ON HOMEPAGE
    var currentDate = moment().format("YYYY-MM-DD");

    $('#from, #to').daterangepicker({
        locale: {
            format: 'YYYY-MM-DD'
        },

        "alwaysShowCalendars": true,
        "minDate": currentDate,
        autoApply: true,
        autoUpdateInput: false,

    }, function (start, end, label) {
        // console.log("New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')");
        // Lets update the fields manually this event fires on selection of range
        var selectedStartDate = start.format('YYYY-MM-DD'); // selected start
        var selectedEndDate = end.format('YYYY-MM-DD'); // selected end

        $checkinInput = $('#from');
        $checkoutInput = $('#to');

        // Updating Fields with selected dates
        $checkinInput.val(selectedStartDate);
        $checkoutInput.val(selectedEndDate);

        // Setting the Selection of dates on calender on CHECKOUT FIELD (To get this it must be binded by Ids not Calss)
        var checkOutPicker = $checkoutInput.data('daterangepicker');
        checkOutPicker.setStartDate(selectedStartDate);
        checkOutPicker.setEndDate(selectedEndDate);

        // Setting the Selection of dates on calender on CHECKIN FIELD (To get this it must be binded by Ids not Calss)
        var checkInPicker = $checkinInput.data('daterangepicker');
        checkInPicker.setStartDate(selectedStartDate);
        checkInPicker.setEndDate(selectedEndDate);

    });

} // End Daterange Picker