$(document).ready(function () {
    
    var seller = {};
    var buyer = {};
    var fundraiser = {};

    //getAllSellers();
    $('#btnModelAddSeller').click(function () {

        seller.companyName = $('#txtCompanyName').val();
        seller.contact = $('#txtContact').val();
        seller.username = $('#txtUsername').val();
        seller.password = $('#txtPassword').val();
        
        var sellerObj = JSON.stringify(seller);
        $.ajax({
            url: "http://localhost:8080/gaget/api/users/seller",
            method: "POST",
            data: sellerObj,
            contentType: 'application/json; charset=utf-8',
            success: function () {
                
                alert('Saved successfully');
                location.replace("seller-list.html");
                $('#sellerAddModal').modal('hide');               
            },
            error: function (error) {
                alert('error', error);
            }
        })
    });

    //buyer
    $('#btnModelAddBuyer').click(function () {

        buyer.companyName = $('#txtFirstNameB').val();
        buyer.companyName = $('#txtFirstNameB').val();
        buyer.contact = $('#txtContactB').val();
        buyer.username = $('#txtUsernameB').val();
        buyer.password = $('#txtPasswordB').val();
        
        var buyerObj = JSON.stringify(buyer);
        $.ajax({
            url: "http://localhost:8080/gaget/api/users/buyer",
            method: "POST",
            data: buyerObj,
            contentType: 'application/json; charset=utf-8',
            success: function () {
                
                alert('Saved successfully');
                location.replace("buyer-list.html");
                $('#buyerAddModal').modal('hide');               
            },
            error: function (error) {
                alert('error', error);
            }
        })
    });

    //fundraiser
    $('#btnModelAddFundraiser').click(function () {

        fundraiser.companyName = $('#txtFirstNameF').val();
        fundraiser.companyName = $('#txtFirstNameF').val();
        fundraiser.contact = $('#txtContactF').val();
        fundraiser.username = $('#txtUsernameF').val();
        fundraiser.password = $('#txtPasswordF').val();
        
        var fundraiserObj = JSON.stringify(fundraiser);
        $.ajax({
            url: "http://localhost:8080/gaget/api/users/fundraiser",
            method: "POST",
            data: fundraiserObj,
            contentType: 'application/json; charset=utf-8',
            success: function () {
                
                alert('Saved successfully');
                location.replace("fundraiser-list.html");
                $('#fundraiserAddModal').modal('hide');               
            },
            error: function (error) {
                alert('error', error);
            }
        })
    });
});

