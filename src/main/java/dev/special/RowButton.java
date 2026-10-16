
package dev.special;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public class RowButton extends ButtonWidget {
    private final Supplier<Boolean> checked;
    private final Supplier<String> value;

    public RowButton(
            int x, int y, int w, int h,
            String label,
            Supplier<Boolean> checked,
            Supplier<String> value,
            PressAction action
    ) {
        super(x, y, w, h, Text.literal(label), action, DEFAULT_NARRATION_SUPPLIER);
        this.checked = checked;
        this.value = value;
    }

    @Override
    protected void drawIcon(DrawContext context, int mouseX, int mouseY, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();

        int x = getX();
        int y = getY();
        int width = getWidth();
        int height = getHeight();

        if (isHovered()) {
            context.fill(x, y, x + width, y + height, 0xFF1D2029);
        }

        int tx = x + 4;
        int ty = y + (height - 8) / 2;

        if (checked != null) {
            if (Boolean.TRUE.equals(checked.get())) {
                context.drawText(mc.textRenderer, Text.literal("✓"),
                        tx, ty, Theme.accent(), false);
            }
            tx += 12;
        }

        context.drawText(mc.textRenderer, getMessage(),
                tx, ty, 0xFFF1F3FA, true);

        if (value != null) {
            String v = value.get();
            if (v != null) {
                context.drawText(mc.textRenderer, Text.literal(v),
                        x + width - 4 - mc.textRenderer.getWidth(v),
                        ty, Theme.accent(), true);
            }
        }
    }
}
