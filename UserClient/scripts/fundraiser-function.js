$(document).ready(function () {
    
    var fundraiser = {};

    getAllFundraisers();
    
})

function getAllFundraisers() {
    $.ajax({
        url: "http://localhost:8080/gaget/api/users/fundraisers",
        method: "GET",
        dataType: "json",
        success: function (data) {
            var tableBody = $('#tblFundraiser tbody');
            tableBody.empty();
            $(data).each(function (index, element) {
                tableBody.append('<tr><td>' + element.userId + '</td><td>' + element.fname +'</td><td>' + element.lname +
                    '</td><td>' + element.contact + '</td><td>' + element.username +
                    '</td> <td><button type="button" class="btn btn-warning btn-sm" onclick= "updateFundraiser(' +
                    element.userId +
                    ')"> Update</button> | <button type="button" class="btn btn-danger btn-sm" onclick= "deleteFundraiser(' +
                    element.userId + ')"> Delete </button></td> </tr>');
            })
        },
        error: function (error) {
            alert(error);
        }
    })
}
//get id value to popup modal
function deleteFundraiser(id) {
    var fundraiser = {};
    
    $.ajax({
        url: 'http://localhost:8080/gaget/api/users/fundraiser/' + id,
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            
            $('#txtUserIdD').val(data.userId);
        },
        error: function (error) {
            alert('error', error);
        }
    });
    $('#fundraiserDeleteModal').modal('show');
}
//trigger delete request
function makeDELETErequest() {
    
    var fundraiserId = $('#txtUserIdD').val();
  
    $.ajax({
        url: 'http://localhost:8080/gaget/api/users/fundraiser/' + fundraiserId,
        method: 'DELETE',
        success: function () {
            alert('record ' + fundraiserId + ' has been deleted');
            location.reload();
        },
        error: function (error) {
            alert(error);
        }
    });
    $('#fundraiserDeleteModal').modal('hide');
}

function updateFundraiser(id) {
    var fundraiser = {};
    
    $.ajax({
        url: 'http://localhost:8080/gaget/api/users/fundraiser/' + id,
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            
            $('#txtFirstNameU').val(data.fname);
            $('#txtLastNameU').val(data.lname);
            $('#txtContactU').val(data.contact);
            $('#txtUsernameU').val(data.username);
            $('#txtUserIdU').val(data.userId);           
        },
        error: function (error) {
            alert('error', error);
        }
    })

    $('#fundraiserUpdateModal').modal('show');
}

function makePUTrequest(){
    var fundraiser = {};

    //fundraiser.companyName = $('#txtCompanyNameU').val();
    fundraiser.fname = $('#txtFirstNameU').val();
    fundraiser.lname = $('#txtLastNameU').val();
    fundraiser.contact = $('#txtContactU').val();
    fundraiser.username = $('#txtUsernameU').val();
    fundraiser.userId = $('#txtUserIdU').val();

    var fundraiserObj = JSON.stringify(fundraiser);

    $.ajax({
    url: 'http://localhost:8080/gaget/api/users/fundraiser/',
    type: 'PUT',
    data: fundraiserObj,
    contentType: 'application/json; charset=utf-8',
    success: function (result) {
        alert('record  has been updated');
        location.reload();
    }
});
$('#fundraiserUpdateModal').modal('hide');
}


        
