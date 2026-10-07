$taskWord = $null
$taskDoc = $null
try {
    $taskWord = New-Object -ComObject Word.Application
    $taskWord.Visible = $false
    $taskWord.DisplayAlerts = 0
    $taskSource = Join-Path $PSScriptRoot 'preguntasLab.docx'
    $taskPdf = Join-Path $PSScriptRoot 'preguntasLab.pdf'
    $taskDoc = $taskWord.Documents.Open($taskSource, $false, $true)
    $taskDoc.ExportAsFixedFormat($taskPdf, 17)
    Write-Output $taskPdf
} finally {
    if ($null -ne $taskDoc) { $taskDoc.Close(0) }
    if ($null -ne $taskWord) { $taskWord.Quit() }
}
