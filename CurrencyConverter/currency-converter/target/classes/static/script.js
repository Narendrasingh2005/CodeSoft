async function convertCurrency() {
    const amount = document.getElementById("amount").value;
    const from = document.getElementById("fromCurrency").value;
    const to = document.getElementById("toCurrency").value;

    // validation
    if (!amount || amount <= 0) {
        document.getElementById("result").innerText = "⚠️ Please enter a valid amount.";
        return;
    }

    try {
        // call Spring Boot backend
        const response = await fetch(`/api/currency/convert?from=${from}&to=${to}&amount=${amount}`);
        
        if (!response.ok) {
            throw new Error("Failed to fetch exchange rate");
        }

        const result = await response.json();

        // show the result
        document.getElementById("result").innerText =
            `${result.amount} ${result.from} = ${result.convertedAmount.toFixed(2)} ${result.to}`;
    } catch (error) {
        document.getElementById("result").innerText = "❌ Error: Could not fetch conversion.";
        console.error(error);
    }
}
