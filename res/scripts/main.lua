function update(c)
    local i = c:requestText("What will you do?")
    if (i == "quit") then
        return "quit"
    else
        c:printText("I didn't understand you.")
    end
    return ""
end