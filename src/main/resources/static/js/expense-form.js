
    console.info(expenseSettings)
    var budget = {reste:10000};
    var budgets = []
    
    function checkExpenseAmount(amount) {
        const confirmations = [];

        const tauxAmount = (budget.reste * expenseSettings.taux) / 100
        console.log("taux :: ");
        console.log(tauxAmount);
        if (amount >= tauxAmount) {
            confirmations.push({'type':1,'title':'Taux d\'alert atteint','message':"Depense a atteint la limitation de taux d'alert de "+expenseSettings.taux+"%"});
        }
        if (amount > budget.reste) {
            confirmations.push({'type':2,'title':'Depassement de budget','message':"Depense depasse la limite du budget : "+budget.reste});
        }
        return confirmations;
    }

    document.getElementById('expense-form').addEventListener('submit', function(event) {
        event.preventDefault();
        const amountInput = document.querySelector('input[name="amount"]');
        const amount = parseFloat(amountInput.value);

        const confirmations = checkExpenseAmount(amount);
        if (confirmations.length > 0) {
            const modal = new bootstrap.Modal(document.getElementById('confirmationModal'));
            const confirmationsDiv = document.getElementById('confirmations');
            confirmations.forEach(confirmation => {
                confirmationsDiv.innerHTML += `<div class="alert alert-warning"><h4>${confirmation.title}</h4><div>${confirmation.message}</div></div>`
            })
            
            
            console.log(confirmations);
            
            modal.show();
            document.getElementById('confirmSubmit').addEventListener('click', () => {
                this.submit();
            });
        } else {
            this.submit();
        }
    }
);



function renderBudgets(){
    var budgetSelect = document.getElementById("budget");
    // clear current options
    budgetSelect.innerHTML = ""+budget.reste;
    // add new options from the response
}
// Dynamic Budget Data
document.getElementById("customerId").addEventListener("change", function() {
    var selectedCustomerId = this.value;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/api/budgets/" + encodeURIComponent(selectedCustomerId), true);
    xhr.setRequestHeader("Accept", "application/json");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            var response = JSON.parse(xhr.responseText);
            if (xhr.status === 200) {
                budget = response.data;
                renderBudgets()
            } else {
                console.error("Failed to load budgets: " + xhr.status);
                console.error(response.errors);
                
            }
        }
    };
    xhr.send();
});
