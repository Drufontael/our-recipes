import { Step } from "@/resource/recipe/step.resource";
import { useState } from "react";

interface StepsManagerProps {
    steps: Step[];
    onAdd: (step: Step) => void;
    onDelete: (step: Step) => void;
}

export const StepsManager: React.FC<StepsManagerProps> = ({
    steps,
    onAdd,
    onDelete,
}) => {
    const [stepText, setStepText] = useState<string>("");

    function handleOnAdd(event: React.FormEvent) {
        event.preventDefault();
        const step = new Step();
        step.description = stepText;
        onAdd(step);
        setStepText(""); // Limpar o campo após adicionar
    }

    return (
        <div className="space-y-6 bg-cream p-6 rounded-lg shadow-md">
            {/* Lista de passos */}
            <ol className="space-y-4">
                {steps.map((step) => (
                    <li
                        key={step.stepNumber}
                        className="flex justify-between items-center bg-brown-300 text-brown-700 rounded-lg p-3 shadow-sm"
                    >
                        <span className="font-medium">{step.description}</span>
                        <button
                            onClick={() => onDelete(step)}
                            className="bg-orange-500 text-white rounded-full px-3 py-1 text-sm font-semibold hover:bg-orange-600"
                        >
                            X
                        </button>
                    </li>
                ))}
            </ol>

            {/* Formulário para adicionar passos */}
            <form
                onSubmit={handleOnAdd}
                className="space-y-4"
            >
                <label htmlFor="new-step" className="block text-brown-500 font-medium">
                    Novo passo
                </label>
                <textarea
                    id="new-step"
                    value={stepText}
                    onChange={(e) => setStepText(e.target.value)}
                    placeholder="Digite o passo"
                    className="w-full border border-brown-400 bg-white rounded-lg p-3 shadow-sm focus:ring-2 focus:ring-brown-500"
                    rows={4} // Define o número de linhas do textarea
                />
                <button
                    type="submit"
                    className="bg-green-500 text-white px-4 py-2 rounded-lg font-semibold hover:bg-green-600"
                >
                    Adicionar passo
                </button>
            </form>
        </div>
    );
};

