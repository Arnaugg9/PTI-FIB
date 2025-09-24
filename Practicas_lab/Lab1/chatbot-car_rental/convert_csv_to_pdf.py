import pandas as pd
from reportlab.lib.pagesizes import letter
from reportlab.platypus import SimpleDocTemplate, Table, TableStyle, Paragraph, Spacer
from reportlab.lib.styles import getSampleStyleSheet
from reportlab.lib import colors

# Cargar el CSV
df = pd.read_csv("dataset/rentals.csv")

# Crear PDF
pdf_file = "dataset/rentals.pdf"
doc = SimpleDocTemplate(pdf_file, pagesize=letter)
styles = getSampleStyleSheet()
elements = []

# Texto introductorio
elements.append(Paragraph("Rental Dataset Report", styles['Title']))
elements.append(Spacer(1, 12))
elements.append(Paragraph("This document contains the rental dataset enriched with additional information for analysis.", styles['Normal']))
elements.append(Spacer(1, 12))

# Convertir DataFrame a tabla
table_data = [df.columns.to_list()] + df.values.tolist()
table = Table(table_data)

# Estilos de la tabla
table.setStyle(TableStyle([
    ('BACKGROUND', (0, 0), (-1, 0), colors.grey),
    ('TEXTCOLOR', (0, 0), (-1, 0), colors.whitesmoke),
    ('ALIGN', (0, 0), (-1, -1), 'CENTER'),
    ('GRID', (0, 0), (-1, -1), 0.25, colors.black),
    ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
    ('BACKGROUND', (0, 1), (-1, -1), colors.beige),
]))

elements.append(table)

# Guardar PDF
doc.build(elements)
print("PDF creado en:", pdf_file)