$(document).ready(function () {

    //第一層選單
    $.ajax({
        url: 'https://raw.githubusercontent.com/donma/TaiwanAddressCityAreaRoadChineseEnglishJSON/master/CityCountyData.json',
        type: "get",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $.each(data, function (key, value) {
                console.log(key, value)
                $('#city').append('<option value="' + key + '">' + data[key].CityName + '</option>')
            })
        },
        error: function (data) {
            alert("fail");
        }
    });

    //第二層選單
    $("#city").change(function () {
        cityvalue = $("#city").val();  //取值
        $("#area").empty(); //清空上次的值

        $.ajax({
            url: 'https://raw.githubusercontent.com/donma/TaiwanAddressCityAreaRoadChineseEnglishJSON/master/CityCountyData.json',
            type: "get",
            dataType: "json",
            success: function (data) {

                eachval = data[cityvalue].AreaList; //鄉鎮
                $('#area').append('<option value="not-choose" disabled selected>-請選擇-</option>')
                $.each(eachval, function (key, value) {
                    $('#area').append('<option value="' + key + '">' + eachval[key].AreaName + '</option>')
                });
            },
            error: function () {
                alert("fail");
            }

        });
    });
    
        //第一層選單
    $.ajax({
        url: 'https://raw.githubusercontent.com/donma/TaiwanAddressCityAreaRoadChineseEnglishJSON/master/CityCountyData.json',
        type: "get",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $.each(data, function (key, value) {
                console.log(key, value)
                $('#city2').append('<option value="' + key + '">' + data[key].CityName + '</option>')
            })
        },
        error: function (data) {
            alert("fail");
        }
    });

    //第二層選單
    $("#city2").change(function () {
        cityvalue = $("#city2").val();  //取值
        $("#area2").empty(); //清空上次的值

        $.ajax({
            url: 'https://raw.githubusercontent.com/donma/TaiwanAddressCityAreaRoadChineseEnglishJSON/master/CityCountyData.json',
            type: "get",
            dataType: "json",
            success: function (data) {

                eachval = data[cityvalue].AreaList; //鄉鎮
                $('#area2').append('<option value="not-choose" disabled selected>-請選擇-</option>')
                $.each(eachval, function (key, value) {
                    $('#area2').append('<option value="' + key + '">' + eachval[key].AreaName + '</option>')
                });
            },
            error: function () {
                alert("fail");
            }

        });
    });
    
});

function getSelectedLabel_city(sel) {
    document.getElementById("selectedLabel_city").value = sel.options[sel.selectedIndex].text;
    console.log(document.getElementById("selectedLabel_city").value);
}
function getSelectedLabel_area(sel) {
    document.getElementById("selectedLabel_area").value = sel.options[sel.selectedIndex].text;
    console.log(document.getElementById("selectedLabel_area").value)
}

function getSelectedLabel_city2(sel) {
    document.getElementById("selectedLabel_city2").value = sel.options[sel.selectedIndex].text;
    console.log(document.getElementById("selectedLabel_city2").value);
}
function getSelectedLabel_area2(sel) {
    document.getElementById("selectedLabel_area2").value = sel.options[sel.selectedIndex].text;
    console.log(document.getElementById("selectedLabel_area2").value)
}